package com.theoparis.lightson.registry

import example.examplemod.LightsOnMod
import net.minecraft.item.Item
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate

object ModItems {
    // use of the new KDeferredRegister
    val REGISTRY = KDeferredRegister(ForgeRegistries.ITEMS, LightsOnMod.ID)

    private fun <T : Item> register(name: String, block: () -> T): ItemReg<T> {
        val ret: ObjectHolderDelegate<T> = REGISTRY.register(name, block) as ObjectHolderDelegate<T>
        return ItemReg(name, ret)
    }

}

data class ItemReg<T : Item>(val name: String, val obj: ObjectHolderDelegate<T>)
