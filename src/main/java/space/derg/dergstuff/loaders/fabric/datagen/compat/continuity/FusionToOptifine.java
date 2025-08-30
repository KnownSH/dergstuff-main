//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.loaders.fabric.datagen.TextureSpliterProvider;

import java.nio.file.Path;

public class FusionToOptifine {
    public static void horizontal(TextureSpliterProvider provider, ResourceLocation texture) {
        provider.splitTexture(texture, "3", 0, 0);
        provider.splitTexture(texture, "0", 1, 0);
        provider.splitTexture(texture, "1", 2, 0);
        provider.splitTexture(texture, "2", 3, 0);
    }

    public static String[] tilesHorizontal(ContinuityPropertiesProvider provider, ResourceLocation texture) {
        Path assetsFolder = provider.getOutput().getModContainer().findPath("assets/" + texture.getNamespace()).get();
        Path absolutePath = assetsFolder.resolve(Path.of("textures", texture.getPath()));

        String[] tiles = new String[4];
        tiles[0] = absolutePath.resolve("/0").toString();
        tiles[1] = absolutePath.resolve("/1").toString();
        tiles[2] = absolutePath.resolve("/2").toString();
        tiles[3] = absolutePath.resolve("/3").toString();
        return tiles;
    }
}
//?}