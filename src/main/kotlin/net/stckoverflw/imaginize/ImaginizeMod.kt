package net.stckoverflw.imaginize

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.stckoverflw.imaginize.commands.addImageCommand
import net.stckoverflw.imaginize.commands.gridSize
import net.stckoverflw.imaginize.commands.resetImages
import net.stckoverflw.imaginize.packets.PacketManager

val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

class ImaginizeMod : ModInitializer, ClientModInitializer {

    companion object {
        var renderImages = true
    }

    override fun onInitializeClient() {
        addImageCommand()
        gridSize()
        resetImages()

        PacketManager.initializeClientSide()
    }

    override fun onInitialize() {
        PacketManager.initializeServerSide()


    }

}