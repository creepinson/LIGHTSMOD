package me.creepinson.lib.proxy;

import me.creepinson.lib.util.Utils;
import me.creepinson.tileentity.TEAlarm;
import me.creepinson.tileentity.TEBulb;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {
	public void registerRenderers() {

	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

		GameRegistry.registerTileEntity(TEBulb.class, new ResourceLocation(Utils.MODID, "teBulb"));
		GameRegistry.registerTileEntity(TEAlarm.class, new ResourceLocation(Utils.MODID, "teAlarm"));
		
	}

	@Override
	public void postInit() {

	}

}