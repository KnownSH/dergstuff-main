//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import com.google.common.hash.HashCode;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.IoSupplier;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class FileCopyProvider implements DataProvider {
    private final FabricDataOutput output;
    private final Map<ResourceLocation, Path> files;

    public FileCopyProvider(FabricDataOutput output) {
        this.output = output;
        this.files = new HashMap<>();
    }

    public FileCopyProvider addFile(ResourceLocation source, String target) {
        return this.addFile(source, Path.of(target));
    }

    public FileCopyProvider addFile(ResourceLocation source, Path target) {
        this.files.put(source, target);
        return this;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.runAsync(() -> {
            for (Map.Entry<ResourceLocation, Path> entry : this.files.entrySet()) {
                Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(entry.getKey().getNamespace());
                if (modContainer.isEmpty())
                    continue;
                Optional<Path> optionalSource = modContainer.get().findPath(entry.getKey().getPath());
                if (optionalSource.isEmpty())
                    continue;

                Path source = optionalSource.get();
                Path target = this.output.getOutputFolder().resolve(entry.getValue());

                try {
                    InputStream supplier = IoSupplier.create(source).get();
                    byte[] buffer = supplier.readAllBytes();
                    output.writeIfNeeded(target, buffer, HashCode.fromBytes(buffer));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public String getName() {
        return "File Copy Provider";
    }
}
//?}