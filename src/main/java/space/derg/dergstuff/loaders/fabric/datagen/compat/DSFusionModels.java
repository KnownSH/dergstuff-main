//? if fabric && fusion {
package space.derg.dergstuff.loaders.fabric.datagen.compat;

import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.ModelInstance;
import com.supermartijn642.fusion.api.model.data.ConnectingModelData;
import com.supermartijn642.fusion.api.model.data.ConnectingModelDataBuilder;
import com.supermartijn642.fusion.api.predicate.DefaultConnectionPredicates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.registry.DSBlocks;

public class DSFusionModels extends DSFusionModelProvider {
    public DSFusionModels(FabricDataOutput output) {
        super(DergStuff.MOD_ID, output);
    }

    @Override
    protected void generate() {
        ConnectingModelData modelData = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/orientable"))
                .texture("front", TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_empty-fusion"))
                .texture("side", TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_side"))
                .texture("top", TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get(), "_top"))
                .connection(DefaultConnectionPredicates.isSameBlock())
                .build();
        ModelInstance<ConnectingModelData> modelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData);
        this.addModel(TextureMapping.getBlockTexture(DSBlocks.COMMERCIAL_SHELF.get()), modelInstance);
    }
}
//?}