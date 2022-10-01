package dev.bodner.jack.demod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class DebugGui {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "TAIL"))
    public void attackSpeedDebugText(MatrixStack matrices, float tickDelta, CallbackInfo ci){
        client.textRenderer.drawWithShadow(matrices, String.valueOf(client.player.getAttackCooldownProgress(0)), 10, 10, 0xffffffff);
        client.textRenderer.drawWithShadow(matrices, String.valueOf(((AttackTickGetter) client.player).getAttackTick()), 10, 20, 0xffffffff);
        client.textRenderer.drawWithShadow(matrices, String.valueOf(client.player.getAttackCooldownProgressPerTick()), 10, 30, 0xffffffff);

    }
}
