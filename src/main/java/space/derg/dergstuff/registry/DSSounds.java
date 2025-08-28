package space.derg.dergstuff.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.common.registry.DergRegistries;
import space.derg.dergstuff.loaders.common.registry.DergRegistry;
import space.derg.dergstuff.loaders.common.registry.RegistryEntry;

public class DSSounds {
    public static final DergRegistry<SoundEvent> SOUNDS = DergRegistries.create(BuiltInRegistries.SOUND_EVENT, DergStuff.MOD_ID);

    public static final RegistryEntry<SoundEvent> DERG_OW = register("derg_ow");

    private static RegistryEntry<SoundEvent> register(String id) {
        ResourceLocation resourceLocation = new ResourceLocation(DergStuff.MOD_ID, id);
        SoundEvent sound = SoundEvent.createVariableRangeEvent(resourceLocation);
        return SOUNDS.register(id, () -> sound);
    }
}