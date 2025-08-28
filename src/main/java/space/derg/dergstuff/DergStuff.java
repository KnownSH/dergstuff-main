package space.derg.dergstuff;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import space.derg.dergstuff.loaders.common.events.RightClickEntityEvent;
import space.derg.dergstuff.loaders.common.events.RightClickItemEvent;
import space.derg.dergstuff.registry.*;

public class DergStuff {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "dergstuff";

    public static void initialize() {
        LOGGER.info("DergStuff initalized");

        DSBlocks.BLOCKS.init();
        DSItems.ITEMS.init();
        DSSounds.SOUNDS.init();
        DSItemGroups.ITEM_GROUPS.init();
        DSEntityTypes.ENTITY_TYPES.init();
    }

    public static InteractionResult onRightClick(RightClickEntityEvent event) {
        if (event.interactionHand() != InteractionHand.MAIN_HAND || !event.player().isCrouching())
            return InteractionResult.PASS;

        ItemStack heldItem = event.player().getMainHandItem();
        if (isDerg(heldItem, event.entity())) {
            Player targetPlayer = (Player) event.entity();

            shearDerg(targetPlayer, event.level(), true);
            applyKnockback(event.player(), event.level(), 1.0F, 6.0F);
            targetPlayer.attack(event.player());

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static InteractionResultHolder<ItemStack> onRightClickItem(RightClickItemEvent event) {
        ItemStack heldItem = event.player().getItemInHand(event.hand());

        if (event.hand() != InteractionHand.MAIN_HAND || !event.player().isCrouching())
            return InteractionResultHolder.pass(heldItem);

        if (isDerg(heldItem, event.player())) {
            shearDerg(event.player(), event.level(), false);
            return InteractionResultHolder.success(heldItem);
        }

        return InteractionResultHolder.pass(heldItem);
    }

    private static boolean isDerg(ItemStack heldItem, Entity entity) {
        return heldItem.is(Items.SHEARS) && entity instanceof Player targetPlayer && targetPlayer.getName().getString().equals("KnownSH");
    }

    private static void shearDerg(Player derg, Level level, Boolean applyKnockback) {
        derg.spawnAtLocation(DSItems.DERG_SCUTE.get());
        applyKnockback(derg, level, applyKnockback ? 0.05F : 0, 0.5F);

        if (!level.isClientSide) {
            BlockPos blockPos = BlockPos.containing(derg.position());
            level.playSound(null, blockPos, SoundEvents.SHEEP_SHEAR, SoundSource.NEUTRAL, 1.0F, 1.0F);
            level.playSound(null, blockPos, DSSounds.DERG_OW.get(), SoundSource.NEUTRAL, 1.4F, 1.0F);
        }
    }

    private static void applyKnockback(Player player, Level level, float strength, float hurt) {
        Vec3 lookAngle = player.getLookAngle();
        player.knockback(strength, lookAngle.x, lookAngle.z);
        player.hurt(level.damageSources().generic(), hurt);
    }
}
