package net.timbocarp.abunchoftotems.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.timbocarp.abunchoftotems.ABunchOfTotems;

public class ModTags {
    public static class Items{
        public static final TagKey<Item> TOTEM_ITEMS =
                createTag("totem_items");

        public static final TagKey<Item> CHARGE_TOTEM_ITEMS =
                createTag("charge_totem_items");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(ABunchOfTotems.MOD_ID, name));
        }
    }
}
