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
import net.timbocarp.abunchoftotems.item.custom.*;

public class ModItems {
    public static final Item TOTEM_OF_FORTUNE = registerItem("totem_of_fortune",
            new TotemOfFortuneItem(new FabricItemSettings().maxDamage(15)));
    public static final Item TOTEM_OF_JAMMING = registerItem("totem_of_jamming",
            new TotemOfJammingItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_RETURNAL = registerItem("totem_of_returnal",
            new TotemOfReturnalItem(new FabricItemSettings().maxDamage(3)));
    public static final Item TOTEM_OF_THE_UNSEEN = registerItem("totem_of_the_unseen",
            new TotemOfTheUnseenItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_BLINKING = registerItem("totem_of_blinking",
            new TotemOfBlinkingItem(new FabricItemSettings().maxDamage(63)));
    public static final Item TOTEM_OF_VITALITY = registerItem("totem_of_vitality",
            new TotemOfVitalityItem(new FabricItemSettings().maxDamage(127)));
    public static final Item TOTEM_OF_RESILIENCE = registerItem("totem_of_resilience",
            new TotemOfResilienceItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_WISDOM = registerItem("totem_of_wisdom",
            new TotemOfWisdomItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_CONDUCTIVITY = registerItem("totem_of_conductivity",
            new TotemOfConductivityItem(new FabricItemSettings().maxDamage(31)));
    public static final Item TOTEM_OF_THE_COSMOS = registerItem("totem_of_the_cosmos",
            new TotemOfTheCosmosItem(new FabricItemSettings().maxDamage(2559)));
    public static final Item TOTEM_OF_THE_DEFIANT = registerItem("totem_of_the_defiant",
            new TotemOfTheDefiantItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_THE_COWARDLY = registerItem("totem_of_the_cowardly",
            new TotemOfTheCowardlyItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_THE_BAT = registerItem("totem_of_the_bat",
            new TotemOfTheBatItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_COMBUSTION = registerItem("totem_of_combustion",
            new TotemOfCombustionItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_ENLIGHTENMENT = registerItem("totem_of_enlightenment",
            new TotemOfEnlightenmentItem(new FabricItemSettings().maxDamage(15)));
    public static final Item TOTEM_OF_GRAVITY = registerItem("totem_of_gravity",
            new TotemOfGravityItem(new FabricItemSettings().maxDamage(1800)));
    public static final Item TOTEM_OF_RIME = registerItem("totem_of_rime",
            new TotemOfRimeItem(new FabricItemSettings().maxDamage(15)));
    public static final Item TOTEM_OF_HELLFIRE = registerItem("totem_of_hellfire",
            new TotemOfHellfireItem(new FabricItemSettings().maxDamage(23)));
    public static final Item TOTEM_OF_THE_BEASTMASTER = registerItem("totem_of_the_beastmaster",
            new TotemOfTheBeastmasterItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_WARDING = registerItem("totem_of_warding",
            new TotemOfWardingItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_THE_SEA = registerItem("totem_of_the_sea",
            new TotemOfTheSeaItem(new FabricItemSettings().maxCount(1)));
    public static final Item TOTEM_OF_FALLING = registerItem("totem_of_falling",
            new TotemOfFallingItem(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ABunchOfTotems.MOD_ID, name), item);
    }

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModItems.TOTEM_OF_VITALITY);
        entries.add(ModItems.TOTEM_OF_RESILIENCE);
        entries.add(ModItems.TOTEM_OF_THE_DEFIANT);
        entries.add(ModItems.TOTEM_OF_THE_COWARDLY);
        entries.add(ModItems.TOTEM_OF_COMBUSTION);
        entries.add(ModItems.TOTEM_OF_RIME);
        entries.add(ModItems.TOTEM_OF_HELLFIRE);
        entries.add(ModItems.TOTEM_OF_THE_BEASTMASTER);
    }

    private static void addItemsToToolsTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModItems.TOTEM_OF_FORTUNE);
        entries.add(ModItems.TOTEM_OF_JAMMING);
        entries.add(ModItems.TOTEM_OF_RETURNAL);
        entries.add(ModItems.TOTEM_OF_THE_UNSEEN);
        entries.add(ModItems.TOTEM_OF_BLINKING);
        entries.add(ModItems.TOTEM_OF_VITALITY);
        entries.add(ModItems.TOTEM_OF_RESILIENCE);
        entries.add(ModItems.TOTEM_OF_WISDOM);
        entries.add(ModItems.TOTEM_OF_CONDUCTIVITY);
        entries.add(ModItems.TOTEM_OF_THE_COSMOS);
        entries.add(ModItems.TOTEM_OF_THE_DEFIANT);
        entries.add(ModItems.TOTEM_OF_THE_COWARDLY);
        entries.add(ModItems.TOTEM_OF_THE_BAT);
        entries.add(ModItems.TOTEM_OF_COMBUSTION);
        entries.add(ModItems.TOTEM_OF_ENLIGHTENMENT);
        entries.add(ModItems.TOTEM_OF_GRAVITY);
        entries.add(ModItems.TOTEM_OF_RIME);
        entries.add(ModItems.TOTEM_OF_HELLFIRE);
        entries.add(ModItems.TOTEM_OF_WARDING);
        entries.add(ModItems.TOTEM_OF_THE_SEA);
        entries.add(ModItems.TOTEM_OF_FALLING);
    }

    public static void registerModItems(){
        ABunchOfTotems.LOGGER.info("Registering mod items for " + ABunchOfTotems.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTabItemGroup);
    }
}
