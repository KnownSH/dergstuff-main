//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen.entries;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class BlockDatagenEntry extends DatagenEntry<Block> {
    private BiConsumer<BlockModelGenerators, Block> modelGenerator = BlockModelGenerators::createTrivialCube;
    private BiConsumer<FabricBlockLootTableProvider, Block> lootGenerator = FabricBlockLootTableProvider::dropSelf;

    public BlockDatagenEntry(Block object, String id) {
        super(object, id);
    }

    @Override
    public void genLang(FabricLanguageProvider.TranslationBuilder builder) {
        builder.add(this.object, this.lang);
    }

    public BlockDatagenEntry model(BiConsumer<BlockModelGenerators, Block> modelGenerator) {
        this.modelGenerator = modelGenerator;
        return this;
    }

    public BlockDatagenEntry loot(BiConsumer<FabricBlockLootTableProvider, Block> lootGenerator) {
        this.lootGenerator = lootGenerator;
        return this;
    }

    public void genBlockModel(BlockModelGenerators generator) {
        if (this.modelGenerator == null)
            return;
        this.modelGenerator.accept(generator, this.object);
    }

    public void genLootTable(FabricBlockLootTableProvider generator) {
        this.lootGenerator.accept(generator, this.object);
    }
}
//?}