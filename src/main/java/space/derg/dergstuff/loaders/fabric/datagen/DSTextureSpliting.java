//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.registry.DSBlocks;

public class DSTextureSpliting extends TextureSpliterProvider {
    public DSTextureSpliting(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        ResourceLocation shelfTexture = TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_empty-fusion");
        this.splitTexture(shelfTexture, "3", 0, 0);
        this.splitTexture(shelfTexture, "0", 1, 0);
        this.splitTexture(shelfTexture, "1", 2, 0);
        this.splitTexture(shelfTexture, "2", 3, 0);
    }
}
//?}