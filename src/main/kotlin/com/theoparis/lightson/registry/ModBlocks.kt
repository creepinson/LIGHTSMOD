package com.theoparis.lightson.registry

import com.theoparis.lightson.block.BulbBlock
import example.examplemod.LightsOnMod
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate

object ModBlocks {
    val REGISTRY = KDeferredRegister(ForgeRegistries.BLOCKS, LightsOnMod.ID)

    val BULB = register("bulb") {
        BulbBlock(
            AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 15f).harvestLevel(2)
                .setOpaque { _: BlockState, _: IBlockReader, _: BlockPos ->
                    true
                }
                .notSolid()
        )
    }

    private fun <T : Block> register(name: String, block: () -> T): BlockReg<T> {
        val ret: ObjectHolderDelegate<T> = REGISTRY.register(name, block) as ObjectHolderDelegate<T>
        ModItems.REGISTRY.register(name) {
            BlockItem(ret.get(), Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
        }
        return BlockReg(name, ret)
    }
}

data class BlockReg<T : Block>(val name: String, val obj: ObjectHolderDelegate<T>)