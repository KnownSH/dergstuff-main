package space.derg.dergstuff.loaders.common.registry;

import net.minecraft.core.Registry;

public class DergRegistries {
    public static <T> DergRegistry<T> create(Registry<T> registry, String id) {
        return new DergRegistry<>(registry, id);
    }
}