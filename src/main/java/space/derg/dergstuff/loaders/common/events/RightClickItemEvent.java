package space.derg.dergstuff.loaders.common.events;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public record RightClickItemEvent(Player player, Level level, InteractionHand hand) {
}