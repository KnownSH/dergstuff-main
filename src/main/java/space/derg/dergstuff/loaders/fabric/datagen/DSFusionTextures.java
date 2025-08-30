//? if fabric && fusion {
package space.derg.dergstuff.loaders.fabric.datagen;

import com.supermartijn642.fusion.api.provider.FusionTextureMetadataProvider;
import com.supermartijn642.fusion.api.texture.DefaultTextureTypes;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureData;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureLayout;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.model.TextureMapping;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.registry.DSBlocks;

public class DSFusionTextures extends FusionTextureMetadataProvider {
    public DSFusionTextures(FabricDataOutput output) {
        super(DergStuff.MOD_ID, output);
    }

    @Override
    protected void generate() {
        ConnectingTextureData textureData = ConnectingTextureData.builder().layout(ConnectingTextureLayout.HORIZONTAL).build();
        this.addTextureMetadata(TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_empty-fusion"), DefaultTextureTypes.CONNECTING, textureData);
        this.addTextureMetadata(TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_top-fusion"), DefaultTextureTypes.CONNECTING, textureData);
    }
}
//?}