package net.timbocarp.abunchoftotems.item.custom;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class TotemOfReturnalItem extends Item {
    public TotemOfReturnalItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean success = false;

        if (user instanceof ServerPlayerEntity serverPlayer) {

            BlockPos spawnpoint = serverPlayer.getSpawnPointPosition();

            if(Objects.equals(world.getDimensionKey().getValue().toString(), serverPlayer.getSpawnPointDimension().getValue().toString())){
                user.teleport(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ());
                serverPlayer.teleport(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ());
                success = true;
            }
            else{
                serverPlayer.sendMessage(Text.literal("Spawnpoint not in this dimension"), false);
            }
        }

        ItemStack itemStack = user.getStackInHand(hand);

        if(success) {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                    SoundCategory.PLAYERS,
                    1.0F,
                    0.5F
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

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_returnal.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
