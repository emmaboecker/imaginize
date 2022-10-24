package net.stckoverflw.minecraftttv.config

import kotlinx.serialization.Serializable
import net.stckoverflw.minecraftttv.image.Grid
import net.stckoverflw.minecraftttv.image.Image

@Serializable
data class ImageSave(
    val images: List<Image>,
    val gridSize: Grid
)
