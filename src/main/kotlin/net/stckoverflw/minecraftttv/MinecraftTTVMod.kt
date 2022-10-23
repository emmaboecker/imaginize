package net.stckoverflw.minecraftttv

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import net.fabricmc.api.ClientModInitializer
import net.stckoverflw.minecraftttv.commands.addImageCommand
import net.stckoverflw.minecraftttv.commands.gridSize

val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

class MinecraftTTVMod : ClientModInitializer {

    override fun onInitializeClient() {
        addImageCommand()
        gridSize()
    }

}