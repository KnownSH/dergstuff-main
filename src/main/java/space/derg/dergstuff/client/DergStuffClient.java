package space.derg.dergstuff.client;

import space.derg.dergstuff.loaders.common.events.RegisterEntityRenderersEvent;
import space.derg.dergstuff.registry.DSEntityRenderers;

public class DergStuffClient {
    public static void initialize() {
        RegisterEntityRenderersEvent.EVENT.addListener(DSEntityRenderers::registerEntityRenders);
    }
}