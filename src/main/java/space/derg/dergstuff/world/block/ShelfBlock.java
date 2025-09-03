package space.derg.dergstuff.world.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ShelfBlock extends Block {
    public static final EnumProperty<ShelfStates> SHELF_STATES = EnumProperty.create("shelftype", ShelfStates.class);

    public ShelfBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(getStateDefinition().any()
                .setValue(SHELF_STATES, ShelfStates.EMPTY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HorizontalDirectionalBlock.FACING, SHELF_STATES);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    public enum ShelfStates implements StringRepresentable {
        EMPTY("empty"),
        OVERLAY1("overlay1"),
        TEST2("overlay2");

        private final String name;

        ShelfStates(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}