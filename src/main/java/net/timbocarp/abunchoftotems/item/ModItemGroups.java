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


                    }).build());

    public static void registerItemGroups() {
        ABunchOfTotems.LOGGER.info("Registering mod item groups for " + ABunchOfTotems.MOD_ID);
    }
}
