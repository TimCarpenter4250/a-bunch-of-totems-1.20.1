package net.timbocarp.abunchoftotems.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TotemOfJammingItem extends Item {
    public TotemOfJammingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        SoundEvent[] discs = {SoundEvents.MUSIC_DISC_CAT, SoundEvents.MUSIC_DISC_FAR, SoundEvents.MUSIC_DISC_BLOCKS, SoundEvents.MUSIC_DISC_CHIRP,
                SoundEvents.MUSIC_DISC_MALL, SoundEvents.MUSIC_DISC_MELLOHI, SoundEvents.MUSIC_DISC_OTHERSIDE, SoundEvents.MUSIC_DISC_PIGSTEP,
                SoundEvents.MUSIC_DISC_RELIC, SoundEvents.MUSIC_DISC_STAL, SoundEvents.MUSIC_DISC_STRAD, SoundEvents.MUSIC_DISC_WAIT, SoundEvents.MUSIC_DISC_WARD};

        int min = 0;
        int max = 12;
        SoundEvent randomDisc = discs[(int) (Math.random() * (max - min + 1)) + min];

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                randomDisc,
                SoundCategory.MUSIC,
                1.0F,
                1.0F
        );

        // particles
        world.addParticle(
                ParticleTypes.NOTE,
                user.getX() + 0.75,
                user.getY() + 1,
                user.getZ() + 0.75,
                0.0,
                0.0,
                0.0
        );
        world.addParticle(
                ParticleTypes.NOTE,
                user.getX() - 0.75,
                user.getY() + 0.7,
                user.getZ() + 0.75,
                0.0,
                0.0,
                0.0
        );
        world.addParticle(
                ParticleTypes.NOTE,
                user.getX() + 0.75,
                user.getY() + 0.5,
                user.getZ() - 0.75,
                0.0,
                0.0,
                0.0
        );
        world.addParticle(
                ParticleTypes.NOTE,
                user.getX() - 0.75,
                user.getY() + 0.3,
                user.getZ() - 0.75,
                0.0,
                0.0,
                0.0
        );

        user.getItemCooldownManager().set(this, 40);

        // kinda ugly but this is the only thing that worked lol
        if(randomDisc == SoundEvents.MUSIC_DISC_CAT){
            user.sendMessage(Text.literal("Now Playing: C418 - cat"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_BLOCKS){
            user.sendMessage(Text.literal("Now Playing: C418 - blocks"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_CHIRP){
            user.sendMessage(Text.literal("Now Playing: C418 - chirp"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_FAR){
            user.sendMessage(Text.literal("Now Playing: C418 - far"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_MALL){
            user.sendMessage(Text.literal("Now Playing: C418 - mall"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_MELLOHI){
            user.sendMessage(Text.literal("Now Playing: C418 - mellohi"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_STAL){
            user.sendMessage(Text.literal("Now Playing: C418 - stal"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_STRAD){
            user.sendMessage(Text.literal("Now Playing: C418 - strad"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_WARD){
            user.sendMessage(Text.literal("Now Playing: C418 - ward"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_WAIT){
            user.sendMessage(Text.literal("Now Playing: C418 - wait"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_OTHERSIDE){
            user.sendMessage(Text.literal("Now Playing: Lena Raine - otherside"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_PIGSTEP){
            user.sendMessage(Text.literal("Now Playing: Lena Raine - Pigstep"), true);
        }
        else if(randomDisc == SoundEvents.MUSIC_DISC_RELIC){
            user.sendMessage(Text.literal("Now Playing: Aaron Cherof - Relic"), true);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
