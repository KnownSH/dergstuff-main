//? if fabric {
package space.derg.dergstuff.loaders.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.client.DergStuffClient;
import space.derg.dergstuff.loaders.common.events.RegisterEntityRenderersEvent;
import space.derg.dergstuff.loaders.common.events.RightClickEntityEvent;
import space.derg.dergstuff.loaders.common.events.RightClickItemEvent;
import space.derg.dergstuff.registry.DSItemGroups;

import java.util.Optional;

public class DergStuffFabric implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        DergStuff.initialize();
        DSItemGroups.addItemGroupEntries();

        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(DergStuff.MOD_ID);

        if (modContainer.isPresent()) {
            ModContainer container = modContainer.get();
            registerBuiltInPack(container, "fusion");
            registerBuiltInPack(container, "athena");
            registerBuiltInPack(container, "continuity");
        }

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) ->
                DergStuff.onRightClick(new RightClickEntityEvent(player, world, hand, entity, hitResult)));

        UseItemCallback.EVENT.register((player, world, hand) ->
                DergStuff.onRightClickItem(new RightClickItemEvent(player, world, hand)));
    }

    @Override
    public void onInitializeClient() {
        DergStuffClient.initialize();
        RegisterEntityRenderersEvent.EVENT.invoke(new RegisterEntityRenderersEvent(EntityRendererRegistry::register));
    }

    private void registerBuiltInPack(ModContainer modContainer, String modid) {
        if (FabricLoader.getInstance().isModLoaded(modid)) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                    new ResourceLocation(DergStuff.MOD_ID, modid),
                    modContainer,
                    Component.literal(modid + " Support for Derg Industries"),
                    ResourcePackActivationType.ALWAYS_ENABLED
            );
        }
    }
}
//?}