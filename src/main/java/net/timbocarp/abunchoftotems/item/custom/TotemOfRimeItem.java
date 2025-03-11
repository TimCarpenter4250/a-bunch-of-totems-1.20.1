package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfRimeItem extends Item {
    public TotemOfRimeItem(Settings settings) {
        super(settings);
    }
    int EFFECT_RADIUS = 12;

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        Box effectArea = new Box(
                user.getX() - EFFECT_RADIUS, user.getY() - EFFECT_RADIUS, user.getZ() - EFFECT_RADIUS,
                user.getX() + EFFECT_RADIUS, user.getY() + EFFECT_RADIUS, user.getZ() + EFFECT_RADIUS
        );

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, effectArea, e -> e != user);

        for (LivingEntity entity : entities) {
            entity.setFrozenTicks(740); // 140 to start freezing + 600 for 15 seconds

            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOWNESS,
                    300,
                    6,
                    true,
                    true,
                    true
            ));

            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.JUMP_BOOST,
                    300,
                    -6,
                    true,
                    true,
                    true
            ));

            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.MINING_FATIGUE,
                    300,
                    10,
                    true,
                    true,
                    true
            ));
        }

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY,
                SoundCategory.NEUTRAL,
                2.0F,
                0.75F
        );
        user.getItemCooldownManager().set(this, 40);

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.setDamage(itemStack.getDamage() + 1);
            if(itemStack.getDamage() == this.getMaxDamage() + 1){
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

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_rime.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
