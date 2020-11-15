package com.theoparis.lightson.registry

import example.examplemod.LightsOnMod
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate

object ModTileEntityTypes {
    val REGISTRY = KDeferredRegister(ForgeRegistries.TILE_ENTITIES, LightsOnMod.ID)

    private fun <T : TileEntity> register(name: String, block: () -> TileEntityType<T>): TileReg<T> {
        val ret: ObjectHolderDelegate<TileEntityType<T>> =
            REGISTRY.register(name, block) as ObjectHolderDelegate<TileEntityType<T>>
        return TileReg(name, ret)
    }
}

data class TileReg<T : TileEntity>(val name: String, val obj: ObjectHolderDelegate<TileEntityType<T>>)