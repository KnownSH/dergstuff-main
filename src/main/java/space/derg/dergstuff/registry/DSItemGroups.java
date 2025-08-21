package space.derg.dergstuff.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.common.registry.DergRegistries;
import space.derg.dergstuff.loaders.common.registry.DergRegistry;
import space.derg.dergstuff.loaders.common.registry.RegistryEntry;

//? if fabric {
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
//?} elif forge {
/*import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
*///?}

public class DSItemGroups {
    public static final DergRegistry<CreativeModeTab> ITEM_GROUPS = DergRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, DergStuff.MOD_ID);

    public static final RegistryEntry<CreativeModeTab> MAIN_TAB = ITEM_GROUPS.register("main_tab", () ->
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("item_group." + DergStuff.MOD_ID + ".main_tab"))
                .icon(() -> new ItemStack(DSBlocks.DERG_WOOL.get().asItem()))
                .displayItems((context, entries) ->
                        DSItems.ITEMS
                                .getEntries()
                                .stream()
                                .map(item -> item.get().getDefaultInstance())
                                .forEach(entries::accept)
                ).build());

    public static void addItemGroupEntries(
            /*? forge*//*BuildCreativeModeTabContentsEvent event*/
    ) {
        //? if fabric
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> {
        //? if forge
        /*if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {*/
            //content.accept();
        }/*? fabric*/);
    }
}