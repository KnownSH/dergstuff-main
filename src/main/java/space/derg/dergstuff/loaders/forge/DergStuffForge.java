//? if forge {
/*package space.derg.dergstuff.loaders.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.registry.DSItemGroups;

@Mod(DergStuff.MOD_ID)
public class DergStuffForge {
    public DergStuffForge() {
        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        DergStuff.initialize();
        eventBus.addListener(DSItemGroups::addItemGroupEntries);
    }
}
*///?}