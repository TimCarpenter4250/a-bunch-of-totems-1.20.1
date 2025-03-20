package net.timbocarp.abunchoftotems.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.timbocarp.abunchoftotems.item.ModItems;

public class ModWanderingTraderModifiers {
    public static void registerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.TOTEM_OF_FORTUNE, 1),
                    1,
                    10,
                    0f
            ));
        });

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.TOTEM_OF_HOLDING, 1),
                    1,
                    10,
                    0f
            ));
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.TOTEM_OF_JAMMING, 1),
                    1,
                    10,
                    0f
            ));
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.TOTEM_OF_SCREAMING, 1),
                    1,
                    10,
                    0f
            ));
        });
    }
}
