package net.timbocarp.abunchoftotems.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.timbocarp.abunchoftotems.ABunchOfTotems;
import net.timbocarp.abunchoftotems.item.custom.TotemOfFortuneItem;
import net.timbocarp.abunchoftotems.item.custom.TotemOfJammingItem;
import net.timbocarp.abunchoftotems.item.custom.TotemOfReturnalItem;
import net.timbocarp.abunchoftotems.item.custom.TotemOfTheUnseenItem;

public class ModItems {
    public static final Item TOTEM_OF_FORTUNE = registerItem("totem_of_fortune",
            new TotemOfFortuneItem(new FabricItemSettings().maxDamage(15)));
    public static final Item TOTEM_OF_JAMMING = registerItem("totem_of_jamming",
            new TotemOfJammingItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_RETURNAL = registerItem("totem_of_returnal",
            new TotemOfReturnalItem(new FabricItemSettings().maxDamage(3)));
    public static final Item TOTEM_OF_THE_UNSEEN = registerItem("totem_of_the_unseen",
            new TotemOfTheUnseenItem(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ABunchOfTotems.MOD_ID, name), item);
    }

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries){

    }

    private static void addItemsToToolsTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModItems.TOTEM_OF_FORTUNE);
        entries.add(ModItems.TOTEM_OF_JAMMING);
        entries.add(ModItems.TOTEM_OF_RETURNAL);
        entries.add(ModItems.TOTEM_OF_THE_UNSEEN);
    }

    public static void registerModItems(){
        ABunchOfTotems.LOGGER.info("Registering mod items for " + ABunchOfTotems.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTabItemGroup);
    }
}
