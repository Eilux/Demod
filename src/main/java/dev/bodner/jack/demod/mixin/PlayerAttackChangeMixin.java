package dev.bodner.jack.demod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerAttackChangeMixin {
    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Inject(method = "attack", at = @At(value = "HEAD"), cancellable = true)
    public void attackCondition(Entity target, CallbackInfo ci){
        if (this.getAttackCooldownProgress(0) != 1.0){
            ci.cancel();
        }
    }
}
