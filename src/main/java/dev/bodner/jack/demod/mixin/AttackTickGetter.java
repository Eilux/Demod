package dev.bodner.jack.demod.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface AttackTickGetter {
    @Accessor("lastAttackedTicks")
    int getAttackTick();
}
