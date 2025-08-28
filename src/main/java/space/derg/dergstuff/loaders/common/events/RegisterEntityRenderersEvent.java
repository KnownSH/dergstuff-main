package space.derg.dergstuff.loaders.common.events;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public record RegisterEntityRenderersEvent(Registrar registry) {
    public static final CommonEvent<RegisterEntityRenderersEvent> EVENT = new CommonEvent<>();

    public <T extends Entity> void register(EntityType<T> type, EntityRendererProvider<T> provider) {
        registry.register(type, provider);
    }

    @FunctionalInterface
    public interface Registrar {
        <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider);
    }
}