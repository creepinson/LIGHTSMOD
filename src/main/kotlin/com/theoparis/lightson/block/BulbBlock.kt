package com.theoparis.lightson.block

import com.theoparis.creepinoutils.util.dropBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.DirectionalBlock
import net.minecraft.block.material.Material
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItemUseContext
import net.minecraft.item.ItemStack
import net.minecraft.state.BooleanProperty
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.util.Direction
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World


class BulbBlock(properties: Properties) : DirectionalBlock(properties) {

    init {
        defaultState = stateContainer.baseState.with(FACING, Direction.UP).with(LIT, false);
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block?, BlockState?>) {
        super.fillStateContainer(builder)
        builder.add(LIT)
        builder.add(FACING);
    }

    override fun onBlockHarvested(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {

        world.removeTileEntity(pos)

        world.dropBlock(this, pos)

        super.onBlockHarvested(world, pos, state, player)
    }

    override fun getLightValue(state: BlockState, world: IBlockReader, pos: BlockPos): Int {
        return if (state.get(LIT))
            15
        else
            0
    }

    override fun neighborChanged(
        state: BlockState,
        world: World,
        pos: BlockPos,
        blockIn: Block,
        fromPos: BlockPos,
        isMoving: Boolean
    ) {
        updateState(world, state, pos)
    }


    override fun onBlockAdded(
        state: BlockState,
        world: World,
        pos: BlockPos,
        oldState: BlockState,
        isMoving: Boolean
    ) {
        updateState(world, state, pos)
    }

    fun updateState(world: World, state: BlockState, pos: BlockPos) {
        if (!world.isRemote) {
            if (state.get(LIT) && !world.isBlockPowered(pos))
                world.setBlockState(
                    pos,
                    state.with(LIT, false),
                    2
                )
            else if (!state.get(LIT) && world.isBlockPowered(pos))
                world.setBlockState(
                    pos,
                    state.with(LIT, true),
                    2
                )
        }
    }


    override fun getStateForPlacement(context: BlockItemUseContext): BlockState {
        val facing = context.face
        return if (facing === Direction.NORTH || facing === Direction.SOUTH || facing === Direction.WEST || facing === Direction.EAST) {
            Blocks.AIR.defaultState
        } else
            defaultState.with(FACING, facing.opposite)
    }


    override fun onBlockPlacedBy(
        world: World,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        stack: ItemStack
    ) {
        if (this.isNextToLeaves(pos, world, false)) {
            world.dropBlock(this, pos)
            world.setBlockState(pos, Blocks.AIR.defaultState)
            return
        }
        // check the block above

        // check the block above
        val statey: BlockState = world.getBlockState(pos.up())
        if (statey.block == this) {
            world.dropBlock(this, pos)
            world.setBlockState(pos, Blocks.AIR.defaultState)
            return
        }
        // check the block above
        // check the block above
        val statey2: BlockState = world.getBlockState(pos.up())
        if (statey2.block == this) {
            world.dropBlock(this, pos)
            world.setBlockState(pos, Blocks.AIR.defaultState)
            return
        }
    }


    private fun isNextToLeaves(pos: BlockPos, world: World, checkNextBlock: Boolean): Boolean {
        // check the block above
        var state: BlockState = world.getBlockState(pos.up())
        if (state.material === Material.LEAVES)
            return true
        else
            if (checkNextBlock) if (isNextToLeaves(pos.up(), world, false)) return false

        // check the block below
        state = world.getBlockState(pos.down())
        if (state.material === Material.LEAVES)
            return true
        else
            if (checkNextBlock) if (isNextToLeaves(pos.up(), world, false)) return false
        // check the nothen block
        state = world.getBlockState(pos.north())
        if (state.material === Material.LEAVES)
            return true
        else
            if (checkNextBlock) if (isNextToLeaves(pos.up(), world, false)) return false
        // check the southen block
        state = world.getBlockState(pos.south())
        if (state.material === Material.LEAVES)
            return true
        else
            if (checkNextBlock) if (isNextToLeaves(pos.up(), world, false)) return false

        // check the eastern block
        state = world.getBlockState(pos.east())
        if (state.material === Material.LEAVES)
            return true
        else
            if (checkNextBlock) if (isNextToLeaves(pos.up(), world, false)) return false

        // Check the western block
        state = world.getBlockState(pos.west())
        if (state.material === Material.LEAVES)
            return true
        else
            if (checkNextBlock) if (isNextToLeaves(pos.up(), world, false)) return false
        return false
    }

    companion object {
        private val DEFAULT_AABB = AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)
        private val NORTH_AABB = AxisAlignedBB(0.25, 0.25, 0.5, 0.75, 0.75, 1.0)
        private val SOUTH_AABB = AxisAlignedBB(0.25, 0.25, 0.0, 0.75, 0.75, 0.5)
        private val WEST_AABB = AxisAlignedBB(0.5, 0.25, 0.25, 1.0, 0.75, 0.75)
        private val EAST_AABB = AxisAlignedBB(0.0, 0.25, 0.25, 0.5, 0.75, 0.75)
        val FACING: DirectionProperty = DirectionProperty.create("facing", Direction.Plane.VERTICAL)
        val LIT: BooleanProperty = BooleanProperty.create("lit")
    }
}