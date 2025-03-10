package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.timbocarp.abunchoftotems.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class AbstractChargeItem extends BowItem {
    public static final Predicate<ItemStack> TOTEM_PROJECTILES = stack -> stack.isIn(ModTags.Items.CHARGE_TOTEM_ITEMS);

    public AbstractChargeItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack itemStack, int remainingUseTicks) {

    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return TOTEM_PROJECTILES;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
