//? if fabric {
package space.derg.dergstuff.loaders.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.fabric.datagen.*;
//? if athena {
import space.derg.dergstuff.loaders.fabric.datagen.DSAthenaBlockStates;
//?}
//? if fusion {
import space.derg.dergstuff.loaders.fabric.datagen.DSContinuityProperties;
import space.derg.dergstuff.loaders.fabric.datagen.DSFusionModels;
import space.derg.dergstuff.loaders.fabric.datagen.DSFusionTextures;
//?}
import space.derg.dergstuff.loaders.fabric.datagen.entries.DatagenEntries;
import space.derg.dergstuff.registry.DSBlocks;
import space.derg.dergstuff.registry.DSItems;

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

        //? if continuity {
        FabricDataGenerator.Pack continuityPack = createResourcePackWithIcon(fabricDataGenerator, "continuity");
        continuityPack.addProvider(DSTextureSpliting::new);
        continuityPack.addProvider(DSContinuityProperties::new);
        //?}

        //? if fusion {
        FabricDataGenerator.Pack fusionPack = createResourcePackWithIcon(fabricDataGenerator, "fusion");
        fusionPack.addProvider(DSFusionTextures::new);
        fusionPack.addProvider(DSFusionModels::new);
        //?}

        //? if athena {
        FabricDataGenerator.Pack athenaPack = createResourcePackWithIcon(fabricDataGenerator, "athena");
        athenaPack.addProvider(DSAthenaBlockStates::new);
        //?}
    }

    private static FabricDataGenerator.Pack createResourcePackWithIcon(FabricDataGenerator generator, String modId) {
        FabricDataGenerator.Pack resourcePack = generator.createBuiltinResourcePack(new ResourceLocation(DergStuff.MOD_ID, modId));
        resourcePack.addProvider((FabricDataGenerator.Pack.Factory<DataProvider>)
                output -> new FileCopyProvider(output).addFile(new ResourceLocation(DergStuff.MOD_ID, modId + "-pack.png"), "pack.png"));
        return resourcePack;
    }
}
//?}