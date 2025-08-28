package space.derg.dergstuff.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.world.items.DergScute;
import space.derg.dergstuff.loaders.common.registry.DergRegistries;
import space.derg.dergstuff.loaders.common.registry.DergRegistry;
import space.derg.dergstuff.loaders.common.registry.RegistryEntry;

import java.util.function.Function;

public class DSItems {
    public static final DergRegistry<Item> ITEMS = DergRegistries.create(BuiltInRegistries.ITEM, DergStuff.MOD_ID);

    public static final RegistryEntry<Item> DERG_SCUTE = registerItem("derg_scute", DergScute::new, new Item.Properties().fireResistant());

    private static RegistryEntry<Item> registerItem(
            String id,
            Function<Item.Properties, Item> function,
            Item.Properties itemProperties
    ) {
        return ITEMS.register(id, () -> function.apply(itemProperties));
    }
}