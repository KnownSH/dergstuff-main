//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen.entries;

import net.minecraft.world.level.block.Block;
import space.derg.dergstuff.loaders.common.registry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class DatagenEntries {
    public final List<BlockDatagenEntry> blockEntries = new ArrayList<>();

    public BlockDatagenEntry block(RegistryEntry<Block> block) {
        BlockDatagenEntry entry = new BlockDatagenEntry(block.get(), block.getId().getPath());
        blockEntries.add(entry);
        return entry;
    }
}
//?}