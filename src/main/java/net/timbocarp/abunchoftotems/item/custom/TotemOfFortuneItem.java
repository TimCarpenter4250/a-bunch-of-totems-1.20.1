package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.world.World;
import net.timbocarp.abunchoftotems.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfFortuneItem extends Item {
    public TotemOfFortuneItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if(!user.getInventory().contains(ModItems.TOTEM_OF_WARDING.getDefaultStack())) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 600));
        }

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_PLAYER_LEVELUP,
                SoundCategory.NEUTRAL,
                1.0F,
                1.5F
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
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_fortune.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
