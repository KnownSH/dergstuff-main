//? if fabric {
package space.derg.dergstuff.loaders.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.fabric.datagen.*;
//? if fusion {
import space.derg.dergstuff.loaders.fabric.datagen.compat.DSFusionModels;
import space.derg.dergstuff.loaders.fabric.datagen.compat.DSFusionTextures;
//?}
import space.derg.dergstuff.loaders.fabric.datagen.entries.DatagenEntries;
import space.derg.dergstuff.registry.DSBlocks;
import space.derg.dergstuff.registry.DSItems;

import java.nio.file.Path;

public class DergStuffDatagen implements DataGeneratorEntrypoint {
    public static final DatagenEntries ENTRIES = new DatagenEntries();

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack datapack = fabricDataGenerator.createPack();

        ENTRIES.block(DSBlocks.DERG_TERRACOTTA).lang("Derg Terracotta");
        ENTRIES.block(DSBlocks.DARK_DERG_TERRACOTTA).lang("Dark Derg Terracotta");
        ENTRIES.block(DSBlocks.DERG_BRICKS).lang("Derg Bricks");
        ENTRIES.block(DSBlocks.DARK_DERG_BRICKS).lang("Dark Derg Bricks");
        ENTRIES.block(DSBlocks.DERG_CONCRETE).lang("Derg Concrete");
        ENTRIES.block(DSBlocks.DARK_DERG_CONCRETE).lang("Dark Derg Concrete");
        ENTRIES.block(DSBlocks.DERG_CONCRETE_POWDER).lang("Derg Concrete Powder");
        ENTRIES.block(DSBlocks.DARK_DERG_CONCRETE_POWDER).lang("Dark Derg Concrete Powder");
        ENTRIES.block(DSBlocks.DERG_WOOL).lang("Derg Wool");
        ENTRIES.block(DSBlocks.DARK_DERG_WOOL).lang("Dark Derg Wool");

        ENTRIES.block(DSBlocks.COMMERCIAL_SHELF)
                .model((generator, block) -> {
                    TextureMapping textureMap = TextureMapping.top(block)
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
                            .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_empty"));
                    ResourceLocation blockModel = ModelTemplates.CUBE_ORIENTABLE.create(block, textureMap, generator.modelOutput);
                    generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, blockModel).with(BlockModelGenerators.createHorizontalFacingDispatch()));
                })
                .lang("Commercial Shelf");

        ENTRIES.item(DSItems.DERG_SCUTE).lang("Derg Scute");

        datapack.addProvider(DSLangEnglish::new);
        datapack.addProvider(DSBlockModels::new);
        datapack.addProvider(DSLootTables::new);
        datapack.addProvider(DSCraftingRecipes::new);

        //? if fusion {
        FabricDataGenerator.Pack fusionPack = fabricDataGenerator.createBuiltinResourcePack(new ResourceLocation(DergStuff.MOD_ID, "fusion"));
        fusionPack.addProvider((FabricDataGenerator.Pack.Factory<FileCopyProvider>) output ->
                new FileCopyProvider(output).addFile(new ResourceLocation(DergStuff.MOD_ID, "fusion-pack.png"), "pack.png"));
        fusionPack.addProvider(DSFusionTextures::new);
        fusionPack.addProvider(DSFusionModels::new);
        //?}
    }
}
//?}