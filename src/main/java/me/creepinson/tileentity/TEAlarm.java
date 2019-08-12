package me.creepinson.tileentity;

import java.util.HashMap;
import java.util.Map;

import elucent.albedo.event.GatherLightsEvent;
import elucent.albedo.lighting.ILightProvider;
import elucent.albedo.lighting.Light;
import me.creepinson.block.Alarm;
import me.creepinson.handler.BlockHandler;
import me.creepinson.handler.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TEAlarm extends TileEntity implements ITickable, ILightProvider {

	private int skullRotation;

	private int ticks;
	private int soundTicks;
	private boolean isOn;
	public static Map<BlockPos, ISound> alarms = new HashMap<BlockPos, ISound>();

	@Override
	public void update() {
		if (this.isInvalid())
			if (ticks > 80) {
				ticks = 0;
			}
		if (soundTicks > 240) {
			soundTicks = 0;
		}
		if (soundTicks == 10 && !this.isInvalid() && world.getBlockState(pos).getBlock() == BlockHandler.alarm
				&& world.getBlockState(pos).getValue(Alarm.LIT)) {
			// alarms.put(pos, new MovingSoundAlarm(this));
			if (world.isRemote) {
				if (Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(alarms.get(pos))) {
					Minecraft.getMinecraft().getSoundHandler().stopSound(alarms.get(pos));
				}
				Minecraft.getMinecraft().getSoundHandler().playSound(alarms.get(pos));
			}
		}

		soundTicks++;

		if (!this.isInvalid() && world.getBlockState(pos).getBlock() == BlockHandler.alarm
				&& world.getBlockState(pos).getValue(Alarm.LIT)) {
			if ((ticks < 60 && ticks > 45) || (ticks < 35 && ticks > 15)) {
				this.isOn = true;
			} else {
				this.isOn = false;
			}
		}
		ticks++;
	}

	@Override
	public Light provideLight() {
		if (this.isOn) {
			return Light.builder().pos(pos.add(0, 0, 0)).pos(pos.add(0, 1, 0)).pos(pos.add(1, 0, 0))
					.pos(pos.add(0, 0, 1)).pos(pos.add(1, 0, 1)).pos(pos.add(1, 1, 1)).color(1, 0, 0, 1.25f).radius(20)
					.build();
		} else {
			return Light.builder().pos(pos.add(0, 0, 0)).color(1, 1, 1, 0).radius(5).build();
		}

	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (this.world.isRemote) {
			alarms.put(pos, new MovingSoundAlarm(this));
		} else {
			alarms.put(pos, new MovingSoundAlarm(this));
		}
	}
	
	public boolean isOn() {
		return this.isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public TEAlarm(boolean isOn) {
		this();
		this.isOn = isOn;
	}

	public TEAlarm() {
		super();
		
	}

	@SideOnly(Side.CLIENT)
	public int getSkullRotation() {
		return this.skullRotation;
	}

	public void setSkullRotation(int rotation) {
		this.skullRotation = rotation;
	}

	@Override
	public void gatherLights(GatherLightsEvent event, Entity entity) {
		if (this.isOn) {
			event.add(Light.builder().pos(pos.add(0, 1, 0)).color(1, 0, 0).radius(5).build());
		}
	}

}