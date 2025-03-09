package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TotemOfTheUnseenItem extends Item {
    public TotemOfTheUnseenItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity holder = (LivingEntity) entity;
        if(selected) {
            holder.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 2, 0, true, false, true));
        }
    }
}
