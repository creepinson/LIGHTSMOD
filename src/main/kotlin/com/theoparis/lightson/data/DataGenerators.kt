package com.theoparis.lightson.data

import com.theoparis.lightson.data.client.ModBlockStateProvider
import com.theoparis.lightson.data.client.ModItemModelProvider
import example.examplemod.LightsOnMod
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent

@Mod.EventBusSubscriber(modid = LightsOnMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {

    @SubscribeEvent()
    fun gatherData(event: GatherDataEvent) {
        val g = event.generator
        val efh = event.existingFileHelper

        g.addProvider(ModItemModelProvider(g, efh))
        g.addProvider(ModBlockStateProvider(g, efh))
    }
}