package net.stckoverflw.imaginize.image

import kotlinx.serialization.Serializable
import net.minecraft.resources.ResourceLocation
import net.silkmc.silk.core.serialization.serializers.ResourceLocationSerializer

@Serializable
data class Image(
    @Serializable(with = ResourceLocationSerializer::class) val resourceLocation: ResourceLocation,
    val link: String,
    val position: Position
) {
    @Serializable
    data class Position(
        val x: Int,
        val y: Int
    )
}
