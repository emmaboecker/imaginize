package net.stckoverflw.minecraftttv

import net.fabricmc.api.ClientModInitializer
import net.stckoverflw.minecraftttv.commands.addImageCommand
import net.stckoverflw.minecraftttv.commands.gridSize

class MinecraftTTVMod : ClientModInitializer {

    override fun onInitializeClient() {
        addImageCommand()
        gridSize()
    }

}