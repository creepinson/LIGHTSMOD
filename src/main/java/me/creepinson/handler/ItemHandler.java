package me.creepinson.handler;

import me.creepinson.lib.util.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ItemHandler {

	public static void init() {

	}

	public static void register() {

	}

	public static void registerRenders() {

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		init();
		event.getRegistry().registerAll();
	}

	public static void registerRender(Item item) {

		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
		Utils.getLogger().info("Registered Render for " + item.getUnlocalizedName().substring(5));

	}

	public static void registerRender(Item item, int meta, String fileName) {

		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(Utils.MODID + ":" + fileName, "inventory"));
		Utils.getLogger().info("Registered Render for " + item.getUnlocalizedName().substring(5));

	}

}