package me.creepinson.packet;

import me.creepinson.block.Alarm;
import me.creepinson.block.Bulb;
import me.creepinson.handler.BlockHandler;
import me.creepinson.handler.SoundHandler;
import me.creepinson.tileentity.MovingSoundAlarm;
import me.creepinson.tileentity.TEAlarm;
import me.creepinson.tileentity.TEBulb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerBulbCheckClient implements IMessageHandler<PacketBulbCheckClient, IMessage> {

	@Override
	public IMessage onMessage(PacketBulbCheckClient message, MessageContext ctx) {

		World world = Minecraft.getMinecraft().player.world;
		BlockPos pos = new BlockPos(message.x, message.y, message.z);
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == BlockHandler.bulb && world.getTileEntity(pos) instanceof TEBulb) {
			if (state.getValue(Bulb.LIT).booleanValue() && !world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(false)), 2);

			} else if (!state.getValue(Bulb.LIT).booleanValue() && world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(Bulb.LIT, Boolean.valueOf(true)), 2);

			}
		} else if (state.getBlock() == BlockHandler.alarm && world.getTileEntity(pos) instanceof TEAlarm) {
			if (state.getValue(Alarm.LIT).booleanValue() && !world.isBlockPowered(pos)) {
				
				world.setBlockState(pos, state.withProperty(Alarm.LIT, Boolean.valueOf(false)), 2);

			} else if (!state.getValue(Alarm.LIT).booleanValue() && world.isBlockPowered(pos)) {
				//MovingSoundAlarm sound = new MovingSoundAlarm ((TEAlarm) world.getTileEntity(pos));
				//Minecraft.getMinecraft().getSoundHandler().playSound(sound);
				world.setBlockState(pos, state.withProperty(Alarm.LIT, Boolean.valueOf(true)), 2);
				
			}
		}
		
		return message;
	}

}
