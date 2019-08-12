package me.creepinson.handler;

import me.creepinson.block.Alarm;
import me.creepinson.block.Bulb;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BlockHandler {

	public static Block bulb = new Bulb(Material.IRON, "bulb", CreativeTabHandler.LIGHTS_ON, 5F, 15F, 2, "pickaxe");
	public static ItemBlock bulbItem = (ItemBlock) new ItemBlock(bulb).setRegistryName(bulb.getRegistryName());

	public static Block alarm = new Alarm(Material.IRON, "alarm", CreativeTabHandler.LIGHTS_ON, 5F, 15F, 2, "pickaxe");
	public static ItemBlock alarmItem = (ItemBlock) new ItemBlock(alarm).setRegistryName(alarm.getRegistryName());

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(bulbItem, alarmItem);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(bulb, alarm);

	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registerRenders();
	}

	public static void registerRenders() {
//		ModelLoader.setCustomStateMapper(bulb, new StateMap.Builder().ignore(Bulb.LIT).build());
		registerRender(bulb);
		registerRender(alarm);

	}

	public static void registerRender(Block block) {

		Item item = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}

}
