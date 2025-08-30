//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.loaders.fabric.datagen.compat.continuity.FusionToOptifine;
import space.derg.dergstuff.registry.DSBlocks;

public class DSTextureSpliting extends TextureSpliterProvider {
    public DSTextureSpliting(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        ResourceLocation shelfTexture = TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_empty-fusion");
        FusionToOptifine.horizontal(this, shelfTexture);
    }
}
//?}