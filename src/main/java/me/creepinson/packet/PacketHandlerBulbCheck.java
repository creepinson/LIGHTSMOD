package me.creepinson.packet;

import me.creepinson.block.Alarm;
import me.creepinson.block.Bulb;
import me.creepinson.handler.BlockHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerBulbCheck implements IMessageHandler<PacketBulbCheck, IMessage> {

	@Override
	public IMessage onMessage(PacketBulbCheck message, MessageContext ctx) {

		World world = ctx.getServerHandler().player.world;
		BlockPos pos = new BlockPos(message.x, message.y, message.z);
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == BlockHandler.bulb) {
			if (state.getValue(Bulb.LIT).booleanValue() && !world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(false)), 2);

			} else if (!state.getValue(Bulb.LIT).booleanValue() && world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(true)), 2);

			}
		} else if (state.getBlock() == BlockHandler.alarm) {
			if (state.getValue(Alarm.LIT).booleanValue() && !world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Alarm.LIT, Boolean.valueOf(false)), 2);

			} else if (!state.getValue(Alarm.LIT).booleanValue() && world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Alarm.LIT, Boolean.valueOf(true)), 2);

			}
		}

		return message;
	}

}
