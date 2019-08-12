package me.creepinson.tileentity.render;

import me.creepinson.tileentity.TEAlarm;
import me.creepinson.tileentity.TEBulb;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRAlarm extends TileEntitySpecialRenderer<TEAlarm>{
	public static TESRAlarm instance;


	public void renderTileEntityAt(TEBulb te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		
	
	
	}

	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
		super.setRendererDispatcher(rendererDispatcherIn);
		instance = this;
	}

	
	
}