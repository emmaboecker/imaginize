package net.stckoverflw.minecraftttv.commands

import net.minecraft.client.Minecraft
import net.silkmc.silk.commands.clientCommand
import net.stckoverflw.minecraftttv.image.Grid
import net.stckoverflw.minecraftttv.image.ImageManager

fun gridSize() = clientCommand("grid-size", true) {
    argument("newSize") { size ->
        runs {
            val newSize: Int = size()

            val screenWidth = Minecraft.getInstance().window.guiScaledWidth.toDouble()
            val screenHeight = Minecraft.getInstance().window.guiScaledHeight.toDouble()

            val ratio = screenHeight / screenWidth

            ImageManager.grid = Grid(newSize, (ratio * newSize.toDouble()).toInt())
        }
    }
}