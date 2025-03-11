package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class TotemOfBlinkingItem extends Item {
    public TotemOfBlinkingItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    public static BlockHitResult getTargetedBlockPos(ServerPlayerEntity player) {
        double maxDistance = 128; // 128 blocks = 8 chunks
        HitResult hit = player.raycast(maxDistance, 0, false);

        if (hit instanceof BlockHitResult blockHit) {
            return blockHit;
        }

        return null; // if no block is targeted
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;

            BlockHitResult targetedBlockResult = getTargetedBlockPos(serverPlayer);
            BlockPos targetedBlock = targetedBlockResult.getBlockPos();

            Block block = world.getBlockState(new BlockPos(targetedBlock.getX(),targetedBlock.getY(),targetedBlock.getZ())).getBlock();

            if (targetedBlock != null &&! Objects.equals(block.toString(), "Block{minecraft:air}")) {
                Direction face = targetedBlockResult.getSide();

                if(Objects.equals(face.toString(), "up")){
                    user.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY() + 1, targetedBlock.getZ() + 0.5);
                    serverPlayer.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY() + 1, targetedBlock.getZ() + 0.5);
                }
                else if(Objects.equals(face.toString(), "down")){
                    user.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY() - 2, targetedBlock.getZ() + 0.5);
                    serverPlayer.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY() - 2, targetedBlock.getZ() + 0.5);
                }
                else if(Objects.equals(face.toString(), "north")){
                    user.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY(), targetedBlock.getZ() - 0.5);
                    serverPlayer.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY(), targetedBlock.getZ() - 0.5);
                }
                else if(Objects.equals(face.toString(), "south")){
                    user.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY(), targetedBlock.getZ() + 1.5);
                    serverPlayer.teleport(targetedBlock.getX() + 0.5, targetedBlock.getY(), targetedBlock.getZ() + 1.5);
                }
                else if(Objects.equals(face.toString(), "east")){
                    user.teleport(targetedBlock.getX() + 1.5, targetedBlock.getY(), targetedBlock.getZ() + 0.5);
                    serverPlayer.teleport(targetedBlock.getX() + 1.5, targetedBlock.getY(), targetedBlock.getZ() + 0.5);
                }
                else if(Objects.equals(face.toString(), "west")){
                    user.teleport(targetedBlock.getX() - 0.5, targetedBlock.getY(), targetedBlock.getZ() + 0.5);
                    serverPlayer.teleport(targetedBlock.getX() - 0.5, targetedBlock.getY(), targetedBlock.getZ() + 0.5);
                }


                world.playSound(
                        null,
                        user.getX(),
                        user.getY(),
                        user.getZ(),
                        SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                        SoundCategory.NEUTRAL,
                        1.0F,
                        1.0F
                );
                user.getItemCooldownManager().set(this, 40);

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!user.getAbilities().creativeMode) {
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

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_blinking.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
