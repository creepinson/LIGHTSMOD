package me.creepinson.tileentity;

import me.creepinson.block.Alarm;
import me.creepinson.handler.BlockHandler;
import me.creepinson.handler.SoundHandler;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MovingSoundAlarm extends MovingSound {
	private final TEAlarm tile;

	public MovingSoundAlarm(TEAlarm tile) {
		super(SoundHandler.alarm, SoundCategory.HOSTILE);
		this.tile = tile;
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		{
			if (this.tile.getWorld().getBlockState(this.tile.getPos()).getBlock() == BlockHandler.alarm
					&& this.tile.getWorld().getBlockState(this.tile.getPos()).getValue(Alarm.LIT)) {
				this.volume = 1.0F;
			} else {
				this.volume = 0.0F;
			}
			if (this.tile.isInvalid()) {
				this.donePlaying = true;
			}
		}
	}
}