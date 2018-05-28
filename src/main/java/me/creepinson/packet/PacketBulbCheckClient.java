package me.creepinson.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketBulbCheckClient implements IMessage {

	public double x, y, z;
	
	public PacketBulbCheckClient(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public PacketBulbCheckClient(BlockPos pos) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}
	
	public PacketBulbCheckClient() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		
	}

}
