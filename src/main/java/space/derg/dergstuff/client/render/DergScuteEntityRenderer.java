package space.derg.dergstuff.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.world.entity.DergScuteProjectile;

public class DergScuteEntityRenderer extends EntityRenderer<DergScuteProjectile> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DergStuff.MOD_ID, "textures/item/derg_scute.png");
    private final ItemRenderer itemRenderer;

    public DergScuteEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DergScuteProjectile entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25)) {
            poseStack.pushPose();
            poseStack.translate(0.0D, 0.04D, 0.0D);
            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            this.itemRenderer.renderStatic(
                    entity.getItem(),
                    ItemDisplayContext.GROUND,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    buffer,
                    entity.level(),
                    entity.getId());
            poseStack.popPose();
        }
    }

    @Override
    public ResourceLocation getTextureLocation(DergScuteProjectile entity) {
        return TEXTURE;
    }
}