package space.derg.dergstuff.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.world.entity.DergScuteProjectile;
import space.derg.dergstuff.loaders.common.registry.DergRegistries;
import space.derg.dergstuff.loaders.common.registry.DergRegistry;
import space.derg.dergstuff.loaders.common.registry.RegistryEntry;

public class DSEntityTypes {
    public static final DergRegistry<EntityType<?>> ENTITY_TYPES = DergRegistries.create(BuiltInRegistries.ENTITY_TYPE, DergStuff.MOD_ID);

    public static final RegistryEntry<EntityType<DergScuteProjectile>> DERG_SCUTE = register("derg_scute", DergScuteProjectile::new);

    private static <E extends Entity> RegistryEntry<EntityType<E>> register(String id, EntityType.EntityFactory<E> builder) {
        return ENTITY_TYPES.register(id, () ->
                EntityType.Builder.of(builder, MobCategory.MISC).sized(0.3F, 0.3F).build(id));
    }
}