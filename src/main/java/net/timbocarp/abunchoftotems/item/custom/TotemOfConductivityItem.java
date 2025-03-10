package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfConductivityItem extends AbstractChargeItem {
    public TotemOfConductivityItem(Settings settings) {
        super(settings);
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
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {
        if(remainingUseTicks == 71975){
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_ARROW_HIT_PLAYER,
                    SoundCategory.NEUTRAL,
                    1.25F,
                    1.15F
            );
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        PlayerEntity player = (PlayerEntity) user;

        if(remainingUseTicks <= 71975) {

            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;

                BlockHitResult targetedBlockResult = getTargetedBlockPos(serverPlayer);
                BlockPos targetedBlock = targetedBlockResult.getBlockPos();

                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightning.setPosition(targetedBlock.toCenterPos());
                world.spawnEntity(lightning);

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!player.getAbilities().creativeMode) {
                    stack.setDamage(stack.getDamage() + 1);
                    if (stack.getDamage() == this.getMaxDamage() + 1) {
                        stack.decrement(1);

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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_conductivity.tooltip1"));
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_conductivity.tooltip2"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
