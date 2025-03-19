package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfScreamingItem extends AbstractChargeItem {
    public TotemOfScreamingItem(Settings settings) {
        super(settings);
    }
    int ticks = 0;

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {
        PlayerEntity player = (PlayerEntity) user;
        this.ticks += 1;

        if(this.ticks % 10 == 0) {
            SoundEvent[] sounds = {SoundEvents.ENTITY_GHAST_HURT, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                    SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundEvents.ENTITY_ENDERMAN_SCREAM, SoundEvents.ENTITY_ENDERMAN_DEATH,
                    SoundEvents.ENTITY_GHAST_HURT, SoundEvents.ENTITY_GHAST_HURT};

            int min = 0;
            int max = 7;
            SoundEvent randomSound = sounds[(int) (Math.random() * (max - min + 1)) + min];

            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    randomSound,
                    SoundCategory.NEUTRAL,
                    5.0F,
                    1.0F
            );

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
        this.ticks = 0;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_screaming.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
