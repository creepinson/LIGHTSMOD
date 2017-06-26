package me.creepinson.util.helper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class WorldHelper {

	

	public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {

        return EnumFacing.getFacingFromVector(

             (float) (entity.posX - clickedBlock.getX()),

             0,//(float) (entity.posY - clickedBlock.getY()),

             (float) (entity.posZ - clickedBlock.getZ()));

    }
}
