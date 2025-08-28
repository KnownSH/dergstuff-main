//? if forge {
/*package space.derg.dergstuff.loaders.forge;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.client.DergStuffClient;
import space.derg.dergstuff.loaders.common.events.RegisterEntityRenderersEvent;
import space.derg.dergstuff.loaders.common.events.RightClickEntityEvent;
import space.derg.dergstuff.loaders.common.events.RightClickItemEvent;
import space.derg.dergstuff.registry.DSItemGroups;

@Mod(DergStuff.MOD_ID)
public class DergStuffForge {
    public DergStuffForge() {
        IEventBus eventBus = MinecraftForge.EVENT_BUS;

        DergStuff.initialize();
        ForgeCommonEvents.initialize();
        eventBus.addListener(DSItemGroups::addItemGroupEntries);


        if (FMLEnvironment.dist == Dist.CLIENT) {
            DergStuffClient.initialize();
            ForgeClient.initialize();
        }
    }

    private static class ForgeCommonEvents {
        public static void initialize() {
            MinecraftForge.EVENT_BUS.register(ForgeCommonEvents.class);
        }

        @SubscribeEvent
        public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
            InteractionResult result = DergStuff.onRightClick(new RightClickEntityEvent(
                    event.getEntity(), event.getLevel(), event.getHand(), event.getTarget(), null
            ));
            if (result != InteractionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result);
            }
        }

        @SubscribeEvent
        public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
            InteractionResultHolder<ItemStack> result = DergStuff.onRightClickItem(new RightClickItemEvent(
                    event.getEntity(), event.getLevel(), event.getHand()
            ));
            if (result.getResult() != InteractionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result.getResult());
            }
        }
    }


    private static class ForgeClient {
        public static void initialize() {
            DergStuff.LOGGER.info("DergStuff Forge Client Initialized");
            IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
            modEventBus.addListener(ForgeClient::onRegisterRenderers);
        }

        private static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            RegisterEntityRenderersEvent.EVENT.invoke(new RegisterEntityRenderersEvent(event::registerEntityRenderer));
        }
    }
}
*///?}