package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.timbocarp.abunchoftotems.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfTheBatItem extends Item {
    public TotemOfTheBatItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity holder = (LivingEntity) entity;
        if(selected || holder.getOffHandStack().isOf(ModItems.TOTEM_OF_THE_BAT)) {
            holder.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 2, 0, true, false, true));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_the_bat.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
