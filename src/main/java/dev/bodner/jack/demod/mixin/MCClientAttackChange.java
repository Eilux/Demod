package dev.bodner.jack.demod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MCClientAttackChange {
    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(method = "doAttack", at = @At(value = "HEAD"), cancellable = true)
    public void attackCancel(CallbackInfoReturnable<Boolean> cir){
        if(player.getAttackCooldownProgress(0) != 1.0){
            cir.setReturnValue(false);
        }
    }
}
