package space.derg.dergstuff.registry;

import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.client.render.DergScuteEntityRenderer;
import space.derg.dergstuff.loaders.common.events.RegisterEntityRenderersEvent;

public class DSEntityRenderers {
    public static void registerEntityRenders(RegisterEntityRenderersEvent event) {
        DergStuff.LOGGER.info("Registering entity renderers");
        event.register(DSEntityTypes.DERG_SCUTE.get(), DergScuteEntityRenderer::new);
    }
}