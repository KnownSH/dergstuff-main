//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import space.derg.dergstuff.loaders.fabric.DergStuffDatagen;

public class DSLootTables extends FabricBlockLootTableProvider {
    public DSLootTables(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        DergStuffDatagen.ENTRIES.blockEntries.forEach(entry -> entry.genLootTable(this));
    }
}
//?}