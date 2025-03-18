package net.timbocarp.abunchoftotems.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.timbocarp.abunchoftotems.ABunchOfTotems;

public class ModItemGroups {
    public static final ItemGroup TOTEMS_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(ABunchOfTotems.MOD_ID, "totems"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.totems"))
                    .icon(() -> new ItemStack(ModItems.TOTEM_OF_FORTUNE)).entries((displayContext, entries) -> {
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
                        entries.add(ModItems.TOTEM_OF_THE_BEASTMASTER);
                        entries.add(ModItems.TOTEM_OF_WARDING);
                        entries.add(ModItems.TOTEM_OF_THE_SEA);
                        entries.add(ModItems.TOTEM_OF_FALLING);
                        entries.add(ModItems.TOTEM_OF_THE_PLAGUE);
                        entries.add(ModItems.TOTEM_OF_NOURISHMENT);
                        entries.add(ModItems.TOTEM_OF_FERTILITY);


                    }).build());

    public static void registerItemGroups() {
        ABunchOfTotems.LOGGER.info("Registering mod item groups for " + ABunchOfTotems.MOD_ID);
    }
}
