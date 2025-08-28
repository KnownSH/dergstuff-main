package space.derg.dergstuff.loaders.common.events;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public record RightClickEntityEvent(Player player, Level level, InteractionHand interactionHand, Entity entity, EntityHitResult entityHitResult) {
    public static final CommonEvent<RightClickEntityEvent> EVENT = new CommonEvent<>();
}