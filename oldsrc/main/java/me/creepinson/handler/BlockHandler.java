package me.creepinson.handler;


import me.creepinson.block.Bulb;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {

	public static Block bulb;
	public static ItemBlock bulbItem;


	public static void init() {

		bulb = new Bulb(Material.ROCK, "bulb",
				CreativeTabHandler.LIGHTS_ON, 5F, 15F, 2, "pickaxe");

		bulbItem = (ItemBlock) new ItemBlock(bulb).setRegistryName(bulb.getRegistryName());
	}

	public static void register() {


		GameRegistry.register(bulb);
		GameRegistry.register(bulbItem);
	}

	public static void registerRenders() {
//		ModelLoader.setCustomStateMapper(bulb, new StateMap.Builder().ignore(Bulb.LIT).build());
		 registerRender(bulb);


	}

	public static void registerRender(Block block) {

		Item item = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}

}
