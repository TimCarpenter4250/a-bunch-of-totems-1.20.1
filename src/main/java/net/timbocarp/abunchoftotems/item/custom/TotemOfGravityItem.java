package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfGravityItem extends AbstractChargeItem {
    public TotemOfGravityItem(Settings settings) {
        super(settings);
    }
    final int EFFECT_RADIUS = 16;

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {
        PlayerEntity player = (PlayerEntity) user;

        Box effectArea = new Box(
                user.getX() - EFFECT_RADIUS, user.getY() - EFFECT_RADIUS, user.getZ() - EFFECT_RADIUS,
                user.getX() + EFFECT_RADIUS, user.getY() + EFFECT_RADIUS, user.getZ() + EFFECT_RADIUS
        );

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, effectArea, e -> e != user);

        for (LivingEntity entity : entities) {
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.LEVITATION,
                    2,
                    1,
                    false,
                    false,
                    true
            ));
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.getAbilities().creativeMode) {
            itemStack.setDamage(itemStack.getDamage() + 1);
            if (itemStack.getDamage() == this.getMaxDamage() + 1) {
                itemStack.decrement(1);

                world.playSound(
                        null,
                        user.getX(),
                        user.getY(),
                        user.getZ(),
                        SoundEvents.ENTITY_ITEM_BREAK,
                        SoundCategory.NEUTRAL,
                        1.0F,
                        1.0F
                );
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_gravity.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
