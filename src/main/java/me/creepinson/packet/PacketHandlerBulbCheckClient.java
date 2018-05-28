package me.creepinson.packet;

import me.creepinson.block.Bulb;
import me.creepinson.handler.BlockHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerBulbCheckClient implements IMessageHandler<PacketBulbCheckClient, IMessage> {

	@Override
	public IMessage onMessage(PacketBulbCheckClient message, MessageContext ctx) {

		World world = Minecraft.getMinecraft().thePlayer.worldObj;
		BlockPos pos = new BlockPos(message.x, message.y, message.z);
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == BlockHandler.bulb) {
			if (state.getValue(Bulb.LIT).booleanValue() && !world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(false)), 2);

			} else if (!state.getValue(Bulb.LIT).booleanValue() && world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(true)), 2);

			}
		}
		return message;
	}

}
