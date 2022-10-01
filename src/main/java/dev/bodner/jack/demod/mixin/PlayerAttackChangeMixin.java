package dev.bodner.jack.demod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerAttackChangeMixin extends LivingEntity {

    protected PlayerAttackChangeMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Shadow public abstract float getAttackCooldownProgressPerTick();

    //Method no longer does what the name says, instead it returns how many ticks must pass before an attack can be made
    @Inject(method = "getAttackCooldownProgressPerTick", at = @At(value = "HEAD"), cancellable = true)
    public void tickProgressChange(CallbackInfoReturnable<Float> cir){
        cir.setReturnValue((float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * 20);
    }

    //This returns a value between 0 and 1 showing percent progress on time until next attack
    @Inject(method = "getAttackCooldownProgress", at = @At(value = "HEAD"), cancellable = true)
    public void attackProgressChange(float baseTime, CallbackInfoReturnable<Float> cir){
        if (getAttackCooldownProgressPerTick() == 0){
            cir.setReturnValue(1.0f);
        }
        else {
            cir.setReturnValue(MathHelper.clamp(lastAttackedTicks/getAttackCooldownProgressPerTick(),0, 1));
        }
    }

    //Prevents an attack from occuring if the attack percentage is less than 100
    @Inject(method = "attack", at = @At(value = "HEAD"), cancellable = true)
    public void attackCondition(Entity target, CallbackInfo ci){
        if (this.getAttackCooldownProgress(0) != 1.0){
            ci.cancel();
        }
    }
}
