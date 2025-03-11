package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfCombustionItem extends Item {
    public TotemOfCombustionItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        if(world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.createExplosion(user, user.getX(), user.getY(), user.getZ(), 12.0F, true, World.ExplosionSourceType.MOB);

            DamageSources damageSources = world.getDamageSources();
            user.damage(damageSources.explosion(null), user.getMaxHealth() * 6);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_combustion.tooltip1"));
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_combustion.tooltip2"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
