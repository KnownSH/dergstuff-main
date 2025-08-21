package space.derg.dergstuff;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import space.derg.dergstuff.registry.DSBlocks;
import space.derg.dergstuff.registry.DSItemGroups;
import space.derg.dergstuff.registry.DSItems;

public class DergStuff {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "dergstuff";

    public static void initialize() {
        LOGGER.info("DergStuff initalized");

        DSBlocks.BLOCKS.init();
        DSItems.ITEMS.init();
        DSItemGroups.ITEM_GROUPS.init();
    }
}
