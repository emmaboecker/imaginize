package net.stckoverflw.minecraftttv.image

import net.minecraft.resources.ResourceLocation

data class Image(
    val resourceLocation: ResourceLocation,
    val position: Position
) {
    data class Position(
        val x: Int,
        val y: Int
    )
}
