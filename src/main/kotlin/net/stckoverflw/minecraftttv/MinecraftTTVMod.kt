package net.stckoverflw.minecraftttv

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import net.fabricmc.api.ClientModInitializer
import net.stckoverflw.minecraftttv.commands.addImageCommand
import net.stckoverflw.minecraftttv.commands.gridSize
import net.stckoverflw.minecraftttv.commands.resetImages

val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

class MinecraftTTVMod : ClientModInitializer {

    companion object {
        var renderImages = true
    }

    override fun onInitializeClient() {
        addImageCommand()
        gridSize()
        resetImages()
    }

}