package me.creepinson.tileentity;

import me.creepinson.block.RGBLightsHelper;
import me.creepinson.core.LightsOnMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TEBulb extends TileEntity implements ITickable {

	private int skullRotation;
	
	private int ticks;

	@Override
	public void update() {
		ticks++;

		

	}

	@SideOnly(Side.CLIENT)
	public int getSkullRotation() {
		return this.skullRotation;
	}

	public void setSkullRotation(int rotation) {
		this.skullRotation = rotation;
	}

}