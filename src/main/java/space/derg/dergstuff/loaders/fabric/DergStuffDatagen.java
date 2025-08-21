//? if fabric {
package space.derg.dergstuff.loaders.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import space.derg.dergstuff.loaders.fabric.datagen.DSBlockModels;
import space.derg.dergstuff.loaders.fabric.datagen.DSLangEnglish;
import space.derg.dergstuff.loaders.fabric.datagen.DSLootTables;
import space.derg.dergstuff.loaders.fabric.datagen.entries.DatagenEntries;
import space.derg.dergstuff.registry.DSBlocks;

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

        datapack.addProvider(DSLangEnglish::new);
        datapack.addProvider(DSBlockModels::new);
        datapack.addProvider(DSLootTables::new);
    }
}
//?}