package com.theoparis.lightson.data.client

import com.theoparis.lightson.registry.ModBlocks
import example.examplemod.LightsOnMod
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.ItemModelBuilder
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelBuilder
import net.minecraftforge.common.data.ExistingFileHelper

class ModItemModelProvider(generator: DataGenerator?, existingFileHelper: ExistingFileHelper?) :
    ItemModelProvider(generator, LightsOnMod.ID, existingFileHelper) {
    override fun registerModels() {
//        withExistingParent(ModBlocks.BULB.id.path, modLoc("block/${ModBlocks.BULB.id.path}"))

        builder("block/${ModBlocks.BULB.name}", ModBlocks.BULB.name).blockItem()
    }

    private fun builder(generated: String, name: String): ItemModelBuilder {
        return getBuilder(name).parent(getExistingFile(modLoc(generated)))
    }

    private fun ItemModelBuilder.blockItem(): ItemModelBuilder {
        return this.transforms().transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
            .rotation(-90f, 0f, 0f)
            .translation(0f, 1f, -3f)
            .scale(0.4f, 0.4f, 0.4f)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
            .rotation(0f, 0f, 0f)
            .translation(0f, 4f, 2f)
            .scale(1f, 1f, 1f)
            .end()
            .end()
    }
}