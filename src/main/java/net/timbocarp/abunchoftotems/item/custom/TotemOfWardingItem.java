package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfWardingItem extends Item {
    public TotemOfWardingItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity holder = (LivingEntity) entity;
        holder.clearStatusEffects();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_warding.tooltip1"));
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_warding.tooltip2"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
