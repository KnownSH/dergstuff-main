package space.derg.dergstuff.world.entity;

import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import space.derg.dergstuff.registry.DSEntityTypes;
import space.derg.dergstuff.registry.DSItems;

public class DergScuteProjectile extends ThrowableItemProjectile {
    private static final double ELASTICITY = 0.7D;
    private int bounceCount = 0;
    private float timeSinceLastBounce = 0;
    private int lifetime = 0;

    public DergScuteProjectile(Level level, LivingEntity shooter) {
        super(DSEntityTypes.DERG_SCUTE.get(), shooter, level);
    }

    public DergScuteProjectile(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected Item getDefaultItem() {
        return DSItems.DERG_SCUTE.get();
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide) {
            this.lifetime++;
            this.timeSinceLastBounce++;
            if (this.lifetime >= 400 || this.bounceCount > 15) {
                dropItem(this);
            }
        }

        Vec3 currentPos = this.position();
        Vec3 velocity = this.getDeltaMovement();
        Vec3 nextPos = currentPos.add(velocity);

        BlockHitResult blockHitResult = this.level().clip(new ClipContext(currentPos, nextPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));

        if (blockHitResult.getType() != HitResult.Type.MISS) {
            this.onHitBlock(blockHitResult);
        }

        super.tick();
    }

    private void dropItem(Entity entity) {
        if (!this.level().isClientSide) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            serverLevel.playSound(this, this.getOnPos(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
        entity.spawnAtLocation(DSItems.DERG_SCUTE.get());
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.bounceCount++;
        this.timeSinceLastBounce = 0;

        Vec3 currentVelocity = this.getDeltaMovement();
        Vec3i hitVector = result.getDirection().getNormal();
        Vec3 hitNormal = new Vec3(hitVector.getX(), hitVector.getY(), hitVector.getZ());

        double dotProduct = currentVelocity.dot(hitNormal);

        Vec3 reflection = currentVelocity.subtract(hitNormal.scale(2 * dotProduct));
        BlockState blockState = this.level().getBlockState(result.getBlockPos());
        Vec3 updatedVelocity = reflection.scale(blockState.is(Blocks.SLIME_BLOCK) ? 1.5 : ELASTICITY);
        this.setDeltaMovement(updatedVelocity);

        if (!this.level().isClientSide && !this.isInWater()) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            serverLevel.playSound(this, this.getOnPos(), SoundEvents.SLIME_SQUISH_SMALL, SoundSource.NEUTRAL,
                    1.0F, 2.0F - (timeSinceLastBounce / 200));
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (!(entity instanceof Player player && player.getName().getString().equals("KnownSH"))) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2.0F);
        }
        dropItem(entity);
    }
}