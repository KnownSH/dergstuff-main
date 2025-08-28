//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import space.derg.dergstuff.registry.DSBlocks;
import space.derg.dergstuff.registry.DSItems;

import java.util.function.Consumer;

public class DSCraftingRecipes extends FabricRecipeProvider {
    public DSCraftingRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {

        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DERG_WOOL.get(), 1)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(ItemTags.WOOL)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DARK_DERG_WOOL.get(), 2)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Items.BLACK_DYE)
                .requires(ItemTags.WOOL)
                .requires(ItemTags.WOOL)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DARK_DERG_CONCRETE_POWDER.get(), 6)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Items.BLACK_DYE)
                .requires(Blocks.SAND, 3)
                .requires(Blocks.GRAVEL, 3)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DERG_CONCRETE_POWDER.get(), 8)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Blocks.SAND, 4)
                .requires(Blocks.GRAVEL, 4)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DARK_DERG_BRICKS.get(), 7)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Items.BLACK_DYE)
                .requires(Blocks.BRICKS, 7)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DERG_BRICKS.get(), 8)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Blocks.BRICKS, 8)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DARK_DERG_TERRACOTTA.get(), 7)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Items.BLACK_DYE)
                .requires(Blocks.TERRACOTTA, 7)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
        new ShapelessRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, DSBlocks.DERG_TERRACOTTA.get(), 8)
                .requires(DSItems.DERG_SCUTE.get())
                .requires(Blocks.TERRACOTTA, 8)
                .unlockedBy("has_scute", has(DSItems.DERG_SCUTE.get()))
                .save(exporter);
    }
}
//?}