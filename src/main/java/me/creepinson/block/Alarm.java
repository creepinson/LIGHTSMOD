package me.creepinson.block;

import me.creepinson.handler.BlockHandler;
import me.creepinson.handler.NetworkHandler;
import me.creepinson.handler.SoundHandler;
import me.creepinson.packet.PacketBulbCheck;
import me.creepinson.packet.PacketBulbCheckClient;
import me.creepinson.tileentity.TEAlarm;
import me.creepinson.tileentity.TEBulb;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class Alarm extends Block implements ITileEntityProvider {
	protected static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.VERTICAL);
	public static final PropertyBool LIT = PropertyBool.create("lit");
	
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, EnumDyeColor color) {
		// TODO Auto-generated method stub
		return super.recolorBlock(world, pos, side, color);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			if (state.getBlock() != Blocks.AIR) {
				NetworkHandler.INSTANCE.sendToServer(new PacketBulbCheck(pos.getX(), pos.getY(), pos.getZ()));
				NetworkHandler.INSTANCE.sendToAllAround(new PacketBulbCheckClient(pos.getX(), pos.getY(), pos.getZ()),
						new TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
			}
		}
	}

	public Alarm(Material mat, String name, CreativeTabs tab, float hardness, float resistance, int harvest,
			String tool) {
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(tool, harvest);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(LIT,
				Boolean.valueOf(false)));

	}

	private int ticks;

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos) {
		if (!world.isRemote) {
			if (state.getBlock() != Blocks.AIR) {
				
				NetworkHandler.INSTANCE.sendToServer(new PacketBulbCheck(pos.getX(), pos.getY(), pos.getZ()));
				NetworkHandler.INSTANCE.sendToAllAround(new PacketBulbCheckClient(pos.getX(), pos.getY(), pos.getZ()),
						new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
			}
		}
	}


	//
	// @Override
	// public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random
	// rand) {
	// if (!worldIn.isRemote) {
	//
	//
	//
	// }
	// if (ticks == 60) {
	//
	// ticks = 0;
	//
	// }
	//
	// ticks++;
	//
	// super.updateTick(worldIn, pos, state, rand);
	// }

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {

		worldIn.removeTileEntity(pos);

		worldIn.scheduleUpdate(pos, this, 2);

		this.dropBlockAsItem(worldIn, pos, state, 0);

		super.onBlockHarvested(worldIn, pos, state, player);
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(LIT,
				Boolean.valueOf((meta & 1) > 0));
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getIndex();

		if (((Boolean) state.getValue(LIT)).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, LIT });
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	/*
	 * @Override public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos,
	 * EnumFacing side) { IBlockState state = worldIn.getBlockState(pos); if (side
	 * == EnumFacing.DOWN && state.getValue(FACING) == EnumFacing.UP) { return true;
	 * } else if (side == EnumFacing.UP && state.getValue(FACING) ==
	 * EnumFacing.DOWN) { return true; } else { return false; } }
	 */

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH || facing == EnumFacing.WEST
				|| facing == EnumFacing.EAST) {
			this.dropBlockAsItem(world, pos, this.getDefaultState(), 0);
			return Blocks.AIR.getDefaultState();
		} else {

			return this.getDefaultState().withProperty(FACING, facing.getOpposite());

		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {

		if (this.isNextToLeaves(pos, world, false)) {
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			return;
		}
		IBlockState statey;
		// check the block above

		statey = world.getBlockState(pos.up());
		if (statey.getBlock().equals(BlockHandler.bulb)) {
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			return;

		}
		// check the block above
		IBlockState statey2;
		statey2 = world.getBlockState(pos.up());
		if (statey2.getBlock().equals(BlockHandler.bulb)) {
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			return;

		}

		// if (this.isNextToBulb(pos, world, false)) {
		// this.dropBlockAsItem(world, pos, state, 0);
		// world.setBlockState(pos, Blocks.AIR.getDefaultState());
		// return;
		//
		// }
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}

	// @Override
	// public IBlockState getStateForPlacement(World world, BlockPos pos,
	// EnumFacing facing, float hitX, float hitY,
	// float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
	// if(facing.getAxis().isVertical()){
	// return this.getDefaultState().withProperty(FACING, facing.getOpposite());
	// }
	// else return this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
	// }

	public boolean isNextToLeaves(BlockPos pos, World world, boolean checkNextBlock) {
		IBlockState state;
		// check the block above
		state = world.getBlockState(pos.up());
		if (state.getBlock().equals(Blocks.LEAVES)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}

		// check the block below
		state = world.getBlockState(pos.down());
		if (state.getBlock().equals(Blocks.LEAVES)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}
		// check the nothen block
		state = world.getBlockState(pos.north());
		if (state.getBlock().equals(Blocks.LEAVES)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}
		// check the southen block
		state = world.getBlockState(pos.south());
		if (state.getBlock().equals(Blocks.LEAVES)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}
		// check the eastern block
		state = world.getBlockState(pos.east());
		if (state.getBlock().equals(Blocks.LEAVES)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}
		// Check the western block
		state = world.getBlockState(pos.west());
		if (state.getBlock().equals(Blocks.LEAVES)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}
		return false;
	}

	public boolean isNextToBulb(BlockPos pos, World world, boolean checkNextBlock) {
		IBlockState state;
		// check the block above
		state = world.getBlockState(pos.up());
		if (state.getBlock().equals(BlockHandler.bulb)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}

		// check the block below
		state = world.getBlockState(pos.down());
		if (state.getBlock().equals(BlockHandler.bulb)) {
			return true;
		} else {
			if (checkNextBlock)
				if (isNextToLeaves(pos.up(), world, false))
					return false;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {

		return new TEAlarm(false);
	}


}
