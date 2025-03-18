package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfFertilityItem extends AbstractChargeItem {
    public TotemOfFertilityItem(Settings settings) {
        super(settings);
    }
    final int RADIUS = 10;
    final int NUM_PARTICLES = 4;
    int ticks = 0;

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {
        PlayerEntity player = (PlayerEntity) user;
        BlockPos playerPos = player.getBlockPos();
        this.ticks += 1;

        if(this.ticks % 15 == 0) {
            for(int i = 0; i < NUM_PARTICLES; i++){
                int min = -1;
                int max = 1;
                double xOffset = Math.random() * (max - min + 1) + min;
                double yOffset = Math.random() * (max - min + 1) + min;
                double zOffset = Math.random() * (max - min + 1) + min;

                world.addParticle(
                        ParticleTypes.HAPPY_VILLAGER,
                        player.getX() + xOffset,
                        player.getY() + 1.25 + yOffset,
                        player.getZ() + zOffset,
                        0,
                        0,
                        0
                );
            }

            if (world instanceof ServerWorld serverWorld) {
                for (BlockPos pos : BlockPos.iterateOutwards(playerPos, RADIUS, 4, RADIUS)) {
                    double distance = Math.sqrt(playerPos.getSquaredDistance(pos));

                    if (distance <= RADIUS) {
                        BlockState state = world.getBlockState(pos);

                        if (state.getBlock() instanceof Fertilizable crop) {
                            if (crop.isFertilizable(world, pos, state, world.isClient)) {
                                crop.grow(serverWorld, world.random, pos, state);
                            }
                        }
                    }
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
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        this.ticks = 0;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_fertility.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
