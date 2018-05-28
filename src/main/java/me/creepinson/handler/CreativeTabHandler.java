package me.creepinson.handler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabHandler {


	
	public static final CreativeTabs LIGHTS_ON = new CreativeTabs("LightsOn"){


		@Override
		public ItemStack getTabIconItem() {
			
			return new ItemStack(BlockHandler.bulbItem);
		};
	    
	};
	
}
