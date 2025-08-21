package space.derg.dergstuff.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.common.registry.DergRegistries;
import space.derg.dergstuff.loaders.common.registry.DergRegistry;
import space.derg.dergstuff.loaders.common.registry.RegistryEntry;

import java.util.function.Function;

public class DSBlocks {
    public static final DergRegistry<Block> BLOCKS = DergRegistries.create(BuiltInRegistries.BLOCK, DergStuff.MOD_ID);

    public static final RegistryEntry<Block>
            DERG_TERRACOTTA = registerCopyBlock("derg_terracotta", Blocks.TERRACOTTA),
            DERG_BRICKS = registerCopyBlock("derg_bricks", Blocks.BRICKS),
            DERG_CONCRETE = registerCopyBlock("derg_concrete", Blocks.WHITE_CONCRETE),
            DERG_CONCRETE_POWDER = registerCopyBlock("derg_concrete_powder", Blocks.WHITE_CONCRETE_POWDER),
            DERG_WOOL = registerCopyBlock("derg_wool", Blocks.WHITE_WOOL),
            DARK_DERG_TERRACOTTA = registerCopyBlock("dark_derg_terracotta", Blocks.TERRACOTTA),
            DARK_DERG_BRICKS = registerCopyBlock("dark_derg_bricks", Blocks.BRICKS),
            DARK_DERG_CONCRETE = registerCopyBlock("dark_derg_concrete", Blocks.WHITE_CONCRETE),
            DARK_DERG_CONCRETE_POWDER = registerCopyBlock("dark_derg_concrete_powder", Blocks.WHITE_CONCRETE_POWDER),
            DARK_DERG_WOOL = registerCopyBlock("dark_derg_wool", Blocks.WHITE_WOOL);

    private static RegistryEntry<Block> registerCopyBlock(String id, Block fromBlock) {
        return registerBlock(id, Block::new, BlockBehaviour.Properties.copy(fromBlock));
    }

    private static RegistryEntry<Block> registerBlock(
            String id,
            Function<BlockBehaviour.Properties, Block> function,
            BlockBehaviour.Properties properties
    ) {
        RegistryEntry<Block> block = BLOCKS.register(id, () -> function.apply(properties));
        DSItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}