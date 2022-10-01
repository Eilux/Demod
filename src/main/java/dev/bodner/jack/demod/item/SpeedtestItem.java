package dev.bodner.jack.demod.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;

public class SpeedtestItem extends Item {
    private final float attackDamage;
    private final float attackSpeed;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    //compensates for default attack and speed values
    public SpeedtestItem(int attackDamage, float attackSpeed, Settings settings) {
        super(settings);
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon damage", this.attackDamage-1, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon speed", this.attackSpeed-4, EntityAttributeModifier.Operation.ADDITION));
        attributeModifiers = builder.build();
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }


}
