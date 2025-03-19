package net.timbocarp.abunchoftotems;

import net.fabricmc.api.ModInitializer;

import net.timbocarp.abunchoftotems.item.ModItemGroups;
import net.timbocarp.abunchoftotems.item.ModItems;
import net.timbocarp.abunchoftotems.util.ModLootTableModifiers;
import net.timbocarp.abunchoftotems.util.ModWanderingTraderModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABunchOfTotems implements ModInitializer {
	public static final String MOD_ID = "abunchoftotems";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModLootTableModifiers.modifyLootTables();
		ModWanderingTraderModifiers.registerTrades();
	}
}