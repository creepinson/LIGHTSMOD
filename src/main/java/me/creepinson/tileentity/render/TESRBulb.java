package me.creepinson.tileentity.render;

import me.creepinson.block.model.ModelCreepinoSkull;
import me.creepinson.tileentity.TEBulb;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBulb extends TileEntitySpecialRenderer<TEBulb> {
	public static TESRBulb instance;


	public void renderTileEntityAt(TEBulb te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);

		this.renderSkull((float) x, (float) y, (float) z, EnumFacing.DOWN, (float) (te.getSkullRotation() * 360) / 16.0F, destroyStage);
		this.renderSkull((float) x, (float) y, (float) z, EnumFacing.UP, (float) (te.getSkullRotation() * 360) / 16.0F, destroyStage);

		
	
	
	}

	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
		super.setRendererDispatcher(rendererDispatcherIn);
		instance = this;
	}

	public void renderSkull(float x, float y, float z, EnumFacing facing, float p_188190_5_, int destroyStage) {

		if (destroyStage >= 0) {
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
			GlStateManager.popMatrix();
		} else {

		

		}

		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		if (facing == EnumFacing.UP) {
			GlStateManager.translate(x + 0.5F, y, z + 0.5F);
		}
		else if (facing == EnumFacing.DOWN) {
			GlStateManager.translate(x + -0.5F, y, z + -0.5F);
		} 
		GlStateManager.popMatrix();
	}
}