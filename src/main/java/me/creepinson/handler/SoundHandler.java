package me.creepinson.handler;

import me.creepinson.lib.util.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SoundHandler {

	public static ResourceLocation alarmLoc = new ResourceLocation(Utils.MODID, "alarm");
	public static SoundEvent alarm = new SoundEvent(alarmLoc).setRegistryName("alarm");

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(alarm);
	}

}
