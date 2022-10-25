package net.stckoverflw.imaginize.utils

import net.minecraft.client.Minecraft
import net.stckoverflw.imaginize.config.SaveManager
import net.stckoverflw.imaginize.image.Grid
import net.stckoverflw.imaginize.image.ImageManager

fun changeGridSize(newSize: Int) {
    val screenWidth = Minecraft.getInstance().window.guiScaledWidth.toDouble()
    val screenHeight = Minecraft.getInstance().window.guiScaledHeight.toDouble()

    val ratio = screenHeight / screenWidth

    ImageManager.grid = Grid(newSize, (ratio * newSize.toDouble()).toInt())
    SaveManager.saveImagesToFile()
}