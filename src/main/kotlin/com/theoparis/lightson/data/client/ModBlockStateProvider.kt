package com.theoparis.lightson.data.client

import com.theoparis.lightson.registry.ModBlocks
import example.examplemod.LightsOnMod
import net.minecraft.data.DataGenerator
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper


class ModBlockStateProvider(gen: DataGenerator?, exFileHelper: ExistingFileHelper?) : BlockStateProvider(
    gen,
    LightsOnMod.ID,
    exFileHelper
) {
    override fun registerStatesAndModels() {
        registerBulb()
    }

    fun registerBulb() {
        val txt = ResourceLocation(LightsOnMod.ID, "block/${ModBlocks.BULB.name}")
        val txtPowered = ResourceLocation(LightsOnMod.ID, "block/${ModBlocks.BULB.name}_powered")

        directionalBlock(ModBlocks.BULB.obj.get()) { state ->
            if (state.get(BlockStateProperties.POWERED))
                return@directionalBlock models().getExistingFile(txtPowered)
            else
                return@directionalBlock models().getExistingFile(txt)
        }
    }
}