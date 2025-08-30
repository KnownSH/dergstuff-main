//? if fabric && athena {
package space.derg.dergstuff.loaders.fabric.datagen.compat.athena;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AthenaBlockStateGenerator extends MultiVariantGenerator {
    private AthenaCTMLayout ctmType;
    private final Map<String, ResourceLocation> textures;

    public AthenaBlockStateGenerator(Block block, List<Variant> variants) {
        super(block, variants);
        this.textures = new HashMap<>();
    }

    public AthenaBlockStateGenerator layout(AthenaCTMLayout ctmType) {
        this.ctmType = ctmType;
        return this;
    }

    public AthenaBlockStateGenerator texture(AthenaTextureMap key, ResourceLocation texture) {
        return this.texture(key.name().toLowerCase(), texture);
    }

    public AthenaBlockStateGenerator texture(String key, ResourceLocation texture) {
        this.textures.put(key, texture);
        return this;
    }

    public AthenaBlockStateGenerator mapToSame(ResourceLocation texture, AthenaTextureMap... keys) {
        Arrays.stream(keys).forEach(key -> this.textures.put(key.name().toLowerCase(), texture));
        return this;
    }

    @Override
    public JsonElement get() {
        JsonObject json = super.get().getAsJsonObject();
        JsonElement ctmLoader = new JsonPrimitive("athena:" + this.ctmType.name().toLowerCase());
        json.add("athena:loader", ctmLoader);

        JsonObject textures = new JsonObject();
        for (Map.Entry<String, ResourceLocation> entry : this.textures.entrySet()) {
            textures.addProperty(entry.getKey(), entry.getValue().toString());
        }
        json.add("ctm_textures", textures);

        return json;
    }
}
//?}