package me.creepinson.handler;

import me.creepinson.core.LightsOnMod;
import me.creepinson.lib.util.Utils;
import me.creepinson.packet.PacketBulbCheck;
import me.creepinson.packet.PacketHandlerBulbCheck;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler 
{	
	public static SimpleNetworkWrapper INSTANCE; 
	
	public static void init()
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Utils.MODID);
		INSTANCE.registerMessage(PacketHandlerBulbCheck.class, PacketBulbCheck.class, 0, Side.SERVER); 		

	}
}