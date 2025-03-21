package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfTheBeastmasterItem extends Item {
    public TotemOfTheBeastmasterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        WolfEntity wolf1 = new WolfEntity(EntityType.WOLF, world);
        wolf1.setPos(user.getX() + 1, user.getY() + 0.5, user.getZ());

        WolfEntity wolf2 = new WolfEntity(EntityType.WOLF, world);
        wolf2.setPos(user.getX() - 1, user.getY() + 0.5, user.getZ() - 1);

        WolfEntity wolf3 = new WolfEntity(EntityType.WOLF, world);
        wolf3.setPos(user.getX() - 1, user.getY() + 0.5, user.getZ() + 1);

        WolfEntity[] wolves = {wolf1, wolf2, wolf3};

        for(int i = 0; i < 3; i++) {
            wolves[i].setTamed(true);
            wolves[i].setOwner(user);
            wolves[i].setCollarColor(DyeColor.WHITE);
            wolves[i].addStatusEffect(new StatusEffectInstance(
                    StatusEffects.STRENGTH,
                    99999999,
                    1,
                    true,
                    false,
                    true
            ));
            wolves[i].addStatusEffect(new StatusEffectInstance(
                    StatusEffects.RESISTANCE,
                    99999999,
                    1,
                    true,
                    false,
                    true
            ));
            // particles
            world.addParticle(
                    ParticleTypes.EXPLOSION,
                    wolves[i].getX(),
                    wolves[i].getY() + 0.15,
                    wolves[i].getZ(),
                    0.0,
                    0.0,
                    0.0
            );

            world.spawnEntity(wolves[i]);
        }

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_GENERIC_EXPLODE,
                SoundCategory.NEUTRAL,
                0.50F,
                1.4F
        );

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_the_beastmaster.tooltip1"));
        tooltip.add(Text.translatable("tooltip.abunchoftotems.totem_of_the_beastmaster.tooltip2"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
