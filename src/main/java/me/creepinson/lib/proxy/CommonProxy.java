package me.creepinson.lib.proxy;

import me.creepinson.handler.BlockHandler;
import me.creepinson.handler.ItemHandler;
import me.creepinson.handler.event.EventHandler;
import me.creepinson.lib.util.Utils;
import me.creepinson.tileentity.TEBulb;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy{
	public void registerRenderers() {
	
	
	}
	@Override
	public void preInit() {
	  
		
		
	}
	
	@Override
	public void init() {
	
	

		GameRegistry.registerTileEntity(TEBulb.class,
				Utils.MODID + ":" + "teBulb");
	}

	@Override
	public void postInit() {
		

		
	}
	
}