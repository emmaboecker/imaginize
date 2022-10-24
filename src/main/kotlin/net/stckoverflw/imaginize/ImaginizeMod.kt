package net.stckoverflw.imaginize

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import net.fabricmc.api.ClientModInitializer
import net.stckoverflw.imaginize.commands.addImageCommand
import net.stckoverflw.imaginize.commands.gridSize
import net.stckoverflw.imaginize.commands.resetImages

val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

class ImaginizeMod : ClientModInitializer {

    companion object {
        var renderImages = true
    }

    override fun onInitializeClient() {
        addImageCommand()
        gridSize()
        resetImages()
    }

}