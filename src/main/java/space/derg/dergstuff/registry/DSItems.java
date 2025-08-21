package space.derg.dergstuff.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.common.registry.DergRegistries;
import space.derg.dergstuff.loaders.common.registry.DergRegistry;

public class DSItems {
    public static final DergRegistry<Item> ITEMS = DergRegistries.create(BuiltInRegistries.ITEM, DergStuff.MOD_ID);
}