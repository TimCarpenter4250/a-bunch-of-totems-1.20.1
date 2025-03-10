package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.timbocarp.abunchoftotems.item.ModItems;

import java.util.function.Predicate;

public class TotemOfVitalityItem extends BowItem {
    public static final Predicate<ItemStack> TOTEM_PROJECTILES = stack -> stack.isOf(ModItems.TOTEM_OF_VITALITY);

    public TotemOfVitalityItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {
        PlayerEntity player = (PlayerEntity) user;

        if(user.getHealth() != user.getMaxHealth()) {
            user.setHealth(user.getHealth() + 1);

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
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return TOTEM_PROJECTILES;
    }
}
