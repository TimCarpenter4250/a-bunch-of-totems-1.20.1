package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.timbocarp.abunchoftotems.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfTheSeaItem extends Item {
    public TotemOfTheSeaItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity holder = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) holder;
        if((selected || holder.getOffHandStack().isOf(ModItems.TOTEM_OF_THE_UNSEEN))){
            if(!player.getInventory().contains(ModItems.TOTEM_OF_WARDING.getDefaultStack())){
                if(player.isSubmergedInWater()) {
                    holder.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 2, 0, true, false, true));
                    holder.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 2, 0, true, false, true));
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_the_sea.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
