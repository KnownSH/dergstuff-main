package space.derg.dergstuff.loaders.common.registry;

import net.minecraft.resources.ResourceLocation;
//? if forge {
/*import net.minecraftforge.registries.RegistryObject;
*///?} elif fabric {
import net.minecraft.core.Registry;
//?}

import java.util.function.Supplier;

public class RegistryEntry<T> implements Supplier<T> {
    //? if fabric {
    private final ResourceLocation id;
    private final T value;

    private RegistryEntry(ResourceLocation id, T value) {
        this.id = id;
        this.value = value;
    }

    public static <T, I extends T> RegistryEntry<I> of(Registry<T> registry, ResourceLocation id, Supplier<I> supplier) {
        return new RegistryEntry<>(id, Registry.register(registry, id, supplier.get()));
    }
    //?} elif forge {
    /*private final RegistryObject<T> object;

    public RegistryEntry(RegistryObject<T> object) {
        this.object = object;
    }
    *///?}

    @Override
    public T get() {
        return /*? fabric {*/this.value/*?} else {*//*object.get()*//*?}*/;
    }

    public ResourceLocation getId() {
        return /*? fabric {*/this.id/*?} else {*//*object.getId()*//*?}*/;
    }
}