//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import space.derg.dergstuff.loaders.fabric.DergStuffDatagen;

public class DSBlockModels extends FabricModelProvider {
    public DSBlockModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        DergStuffDatagen.ENTRIES.blockEntries.forEach(blockEntry -> blockEntry.genBlockModel(blockStateModelGenerator));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        DergStuffDatagen.ENTRIES.itemEntries.forEach(itemEntry -> itemModelGenerator.generateFlatItem(itemEntry.getItem(), ModelTemplates.FLAT_ITEM));
    }
}
//?}