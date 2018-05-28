package me.creepinson.packet;

import me.creepinson.block.Bulb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerBulbCheck implements IMessageHandler<PacketBulbCheck, IMessage> {

	@Override
	public IMessage onMessage(PacketBulbCheck message, MessageContext ctx) {
		
		World world = ctx.getServerHandler().playerEntity.worldObj;
		BlockPos pos = new BlockPos(message.x, message.y, message.z);
		IBlockState state = world.getBlockState(pos);
		if (state.getValue(Bulb.LIT).booleanValue() && !world.isBlockPowered(pos)) {
			world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(false)), 2);

		} else if (!state.getValue(Bulb.LIT).booleanValue() && world.isBlockPowered(pos)) {
			world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(true)), 2);

		}
		
		return message;
	}


}
