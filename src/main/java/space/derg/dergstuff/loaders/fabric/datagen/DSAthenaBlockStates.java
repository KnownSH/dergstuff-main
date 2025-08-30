//? if fabric && athena {
package space.derg.dergstuff.loaders.fabric.datagen;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import space.derg.dergstuff.loaders.fabric.datagen.compat.athena.AthenaBlockStateGenerator;
import space.derg.dergstuff.loaders.fabric.datagen.compat.athena.AthenaCTMLayout;
import space.derg.dergstuff.loaders.fabric.datagen.compat.athena.AthenaTextureMap;
import space.derg.dergstuff.registry.DSBlocks;

public class DSAthenaBlockStates extends FabricModelProvider {
    public DSAthenaBlockStates(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        Block shelf = DSBlocks.COMMERCIAL_SHELF.get();
        ResourceLocation blockModel = TextureMapping.getBlockTexture(shelf).withSuffix("_empty-fusion/"); // this is so dumb but it works so well im not changing it
        generator.blockStateOutput.accept(new AthenaBlockStateGenerator(
                shelf, ImmutableList.of(Variant.variant().with(VariantProperties.MODEL, blockModel)))
                .layout(AthenaCTMLayout.CTM)
                .mapToSame(blockModel.withSuffix("0"), AthenaTextureMap.CENTER, AthenaTextureMap.EMPTY, AthenaTextureMap.HORIZONTAL)
                .mapToSame(blockModel.withSuffix("1"), AthenaTextureMap.VERTICAL, AthenaTextureMap.PARTICLE));
        generator.skipAutoItemBlock(shelf);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {}
}
//?}