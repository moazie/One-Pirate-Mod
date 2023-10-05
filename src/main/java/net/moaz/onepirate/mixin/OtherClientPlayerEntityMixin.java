package net.moaz.onepirate.mixin;
import net.minecraft.client.network.OtherClientPlayerEntity;

import net.moaz.onepirate.OnePirateClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OtherClientPlayerEntity.class)
public class OtherClientPlayerEntityMixin {

    OtherClientPlayerEntity otherClientPlayerEntity = ((OtherClientPlayerEntity) (Object) this);
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci){
        if(otherClientPlayerEntity != null && otherClientPlayerEntity.getItemUseTime() > 31) {
            if (OnePirateClient.itemUseTime < 31) {

                ++OnePirateClient.itemUseTime;

            } else {
                OnePirateClient.itemUseTime = 0;
            }
        }
    }
}
