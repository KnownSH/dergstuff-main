//? if fabric && fusion {
package space.derg.dergstuff.loaders.fabric.datagen.compat;

import com.google.gson.JsonObject;
import com.supermartijn642.fusion.api.model.FusionModelTypeRegistry;
import com.supermartijn642.fusion.api.model.ModelInstance;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class DSFusionModelProvider implements DataProvider {
    private final Map<ResourceLocation, ModelInstance<?>> models = new HashMap<>();
    private final String modName;
    private final FabricDataOutput output;

    public DSFusionModelProvider(String modid, FabricDataOutput output) {
        this.modName = FabricLoader.getInstance().getModContainer(modid).map(ModContainer::getMetadata).map(ModMetadata::getName).orElse(modid);
        this.output = output;
    }

    public final CompletableFuture<?> run(CachedOutput cache) {
        this.generate();

        Path output = this.output.getOutputFolder();

        List<? extends CompletableFuture<?>> tasks = this.models.entrySet().stream()
                .map(entry -> {
                    ResourceLocation id = entry.getKey();
                    ModelInstance<?> model = entry.getValue();
                    Path modelPath = getModelPath(output, id);
                    JsonObject serialized = FusionModelTypeRegistry.serializeModelData(model);
                    ConditionJsonProvider.write(serialized, DefaultResourceConditions.allModsLoaded("fusion"));

                    return DataProvider.saveStable(cache, serialized, modelPath);
                })
                .toList();

        return CompletableFuture.allOf(tasks.toArray(CompletableFuture[]::new));
    }

    private static Path getModelPath(Path rootOutput, ResourceLocation id) {
        String modelPath = id.getPath();
        boolean hasExtension = modelPath.lastIndexOf(".") > modelPath.lastIndexOf("/");
        String pathWithExtension = hasExtension ? modelPath : modelPath + ".json";

        return rootOutput.resolve(Path.of("assets", id.getNamespace(), "models", pathWithExtension));
    }

    protected abstract void generate();

    public final void addModel(net.minecraft.resources.ResourceLocation location, ModelInstance<?> model) {
        ModelInstance<?> previousValue = this.models.put(location, model);
        if (previousValue != null) {
            throw new RuntimeException("Duplicate model for '" + location + "'!");
        }
    }

    @Override
    public String getName() {
        return "Fusion Model Provider: " + this.modName;
    }
}
//?}