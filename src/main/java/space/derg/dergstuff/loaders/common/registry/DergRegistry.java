package space.derg.dergstuff.loaders.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

//? if forge {
/*import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
*///?}

import java.util.Collection;
import java.util.function.Supplier;

public class DergRegistry<T> {
    private final RegistryEntries<T> entries = new RegistryEntries<>();

    //? if fabric {
    private final Registry<T> registry;
    private final String id;
    //?} elif forge {
    /*private final DeferredRegister<T> register;
    *///?}

    public DergRegistry(Registry<T> registry, String id) {
        //? if fabric {
        this.registry = registry;
        this.id = id;
        //?} elif forge {
        /*this.register = DeferredRegister.create(registry.key(), id);
        *///?}
    }

    public <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier) {
        //? if fabric
        return entries.add(RegistryEntry.of(this.registry, new ResourceLocation(this.id, id), supplier));
        //? if forge
        /*return this.entries.add(new RegistryEntry<>(register.register(id, supplier)));*/
    }

    public Collection<RegistryEntry<T>> getEntries() {
        return this.entries.getEntries();
    }

    public void init() {
        //? if forge {
        /*IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        register.register(bus);
        *///?}
    }
}