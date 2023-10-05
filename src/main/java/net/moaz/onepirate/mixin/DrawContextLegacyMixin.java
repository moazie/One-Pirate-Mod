package net.moaz.onepirate.mixin;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawContext.class)
public abstract class DrawContextLegacyMixin implements SynchronousResourceReloader {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    @Final
    private MatrixStack matrices;

    @Shadow
    @Final
    private VertexConsumerProvider.Immediate vertexConsumers;

    @Shadow
    public void draw() {
        RenderSystem.disableDepthTest();
        this.vertexConsumers.draw();
        RenderSystem.enableDepthTest();
    }

    @Shadow
    public VertexConsumerProvider.Immediate getVertexConsumers() {
        return this.vertexConsumers;
    }
}
