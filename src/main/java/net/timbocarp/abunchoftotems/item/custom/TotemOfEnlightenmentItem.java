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
import net.timbocarp.abunchoftotems.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfEnlightenmentItem extends Item {
    public TotemOfEnlightenmentItem(Settings settings) {
        super(settings);
    }
    final int EFFECT_RADIUS = 50;

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean good = true;

        Box effectArea = new Box(
                user.getX() - EFFECT_RADIUS, user.getY() - EFFECT_RADIUS, user.getZ() - EFFECT_RADIUS,
                user.getX() + EFFECT_RADIUS, user.getY() + EFFECT_RADIUS, user.getZ() + EFFECT_RADIUS
        );

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, effectArea, e -> e != user);

        for (LivingEntity entity : entities) {
            if(entity instanceof PlayerEntity){
                if(((PlayerEntity) entity).getInventory().contains(ModItems.TOTEM_OF_WARDING.getDefaultStack())){
                    good = false;
                }
            }

            if(good) {
                entity.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.GLOWING,
                        400,
                        0,
                        false,
                        false,
                        true
                ));
            }

            good = true;
        }

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.BLOCK_BEACON_ACTIVATE,
                SoundCategory.NEUTRAL,
                2.0F,
                2.0F
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
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_enlightenment.tooltip1"));
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_enlightenment.tooltip2"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
