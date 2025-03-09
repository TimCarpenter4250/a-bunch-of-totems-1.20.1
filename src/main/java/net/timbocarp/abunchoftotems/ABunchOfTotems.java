package net.timbocarp.abunchoftotems;

import net.fabricmc.api.ModInitializer;

import net.timbocarp.abunchoftotems.item.ModItems;
import net.timbocarp.abunchoftotems.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABunchOfTotems implements ModInitializer {
	public static final String MOD_ID = "abunchoftotems";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
	}
}