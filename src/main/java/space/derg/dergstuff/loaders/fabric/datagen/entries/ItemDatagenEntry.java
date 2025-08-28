//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen.entries;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class ItemDatagenEntry extends DatagenEntry<Item> {
    private BiConsumer<ItemModelGenerators, Item> modelGenerator;

    public ItemDatagenEntry(Item object, String id) {
        super(object, id);
    }

    @Override
    public void genLang(FabricLanguageProvider.TranslationBuilder builder) {
        builder.add(this.object, this.lang);
    }

    public Item getItem() {
        return this.object;
    }
}
//?}