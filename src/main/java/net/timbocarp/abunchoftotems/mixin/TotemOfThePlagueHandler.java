package net.timbocarp.abunchoftotems.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.timbocarp.abunchoftotems.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class TotemOfThePlagueHandler {

    @Inject(method = "onAttacking", at = @At("HEAD"))
	private void applyEffectOnAttack(Entity target, CallbackInfo ci) {
		if (target instanceof PlayerEntity player) {
            if(player.getOffHandStack().isOf(ModItems.TOTEM_OF_THE_PLAGUE) || player.getMainHandStack().isOf(ModItems.TOTEM_OF_THE_PLAGUE)) {
				LivingEntity attacker = (LivingEntity) (Object) this;

                int DURATION = 140;
                if(attacker instanceof PlayerEntity attackingPlayer){
					if(!attackingPlayer.getInventory().contains(ModItems.TOTEM_OF_WARDING.getDefaultStack())){
						attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, DURATION, 1));
					}
				}
				else {
					attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, DURATION, 1));
				}
			}
		}
	}
}