package me.creepinson.core;

import java.util.Random;

import me.creepinson.handler.BlockHandler;
import me.creepinson.handler.ItemHandler;
import me.creepinson.handler.NetworkHandler;
import me.creepinson.lib.proxy.CommonProxy;
import me.creepinson.lib.util.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = Utils.MODID, version = Utils.VERSION)
public class LightsOnMod {
	@SidedProxy(serverSide = Utils.SEVERPROXY, clientSide = Utils.CLIENTPROXY)
	public static CommonProxy proxy;
	@Mod.Instance(Utils.MODID)
	public static LightsOnMod instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {


		Utils.getLogger().info("Pre Init");

		proxy.preInit();
		proxy.registerRenderers();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkHandler.init();
		Utils.getLogger().info("Init");
		FMLCommonHandler.instance().bus().register(new me.creepinson.handler.event.EventHandler());
		MinecraftForge.EVENT_BUS.register(new me.creepinson.handler.event.EventHandler());
		proxy.init();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		Utils.getLogger().info("Post Init");

		proxy.postInit();

	}

	public static void registerEntity(Class entityClass, String name, int ID, int color1, int color2) {

		long seed = name.hashCode();
		Random rand = new Random(seed);
		int primaryColor = rand.nextInt() * 16777215;
		int secondaryColor = rand.nextInt() * 16777215;

		EntityRegistry.registerModEntity(new ResourceLocation(Utils.MODID, name), entityClass, name, ID, instance, 64,
				10, true, color1, color2);
	}

	public static void registerEntityNoEgg(Class entityClass, String name, int ID) {

		long seed = name.hashCode();
		Random rand = new Random(seed);
		int primaryColor = rand.nextInt() * 16777215;
		int secondaryColor = rand.nextInt() * 16777215;

		EntityRegistry.registerModEntity(new ResourceLocation(Utils.MODID, name), entityClass, name, ID, instance, 64,
				10, true);

	}

}
