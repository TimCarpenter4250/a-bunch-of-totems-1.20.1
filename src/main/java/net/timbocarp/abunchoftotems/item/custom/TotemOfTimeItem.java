package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfTimeItem extends AbstractChargeItem {
    public TotemOfTimeItem(Settings settings) {
        super(settings);
    }
    ClientWorld clientWorld;
    ServerWorld serverWorld;

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {
        PlayerEntity player = (PlayerEntity) user;

        if(world instanceof ClientWorld){
            clientWorld = (ClientWorld) world;
            clientWorld.setTimeOfDay(clientWorld.getTimeOfDay() + 40L);
        }

        if(world instanceof ServerWorld) {
            serverWorld = (ServerWorld) world;
            serverWorld.setTimeOfDay(clientWorld.getTimeOfDay());
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
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_time.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
