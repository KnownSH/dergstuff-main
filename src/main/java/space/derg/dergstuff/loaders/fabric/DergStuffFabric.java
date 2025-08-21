//? if fabric {
package space.derg.dergstuff.loaders.fabric;

import net.fabricmc.api.ModInitializer;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.registry.DSItemGroups;

public class DergStuffFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DergStuff.initialize();
        DSItemGroups.addItemGroupEntries();
    }
}
//?}