package net.stckoverflw.imaginize.config

import kotlinx.serialization.Serializable
import net.stckoverflw.imaginize.image.Grid
import net.stckoverflw.imaginize.image.Image

@Serializable
data class ImageSave(
    val images: List<Image>,
    val gridSize: Grid
)
