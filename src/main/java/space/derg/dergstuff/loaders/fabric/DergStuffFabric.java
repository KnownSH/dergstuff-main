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
import net.fabricmc.loader.impl.util.StringUtil;
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

        // Priority list, Fusion > Continuity > Athena
        if (modContainer.isPresent()) {
            ModContainer container = modContainer.get();
            boolean fusionLoaded = registerBuiltInPack(container, "fusion");
            if (!fusionLoaded) {
                registerBuiltInPack(container, "athena");
            }
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

    private boolean registerBuiltInPack(ModContainer modContainer, String modid) {
        boolean isModLoaded = FabricLoader.getInstance().isModLoaded(modid);
        if (isModLoaded) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                    new ResourceLocation(DergStuff.MOD_ID, modid),
                    modContainer,
                    Component.literal(StringUtil.capitalize(modid) + " Support For Derg Stuff"),
                    ResourcePackActivationType.NORMAL
            );
        }
        return isModLoaded;
    }
}
//?}