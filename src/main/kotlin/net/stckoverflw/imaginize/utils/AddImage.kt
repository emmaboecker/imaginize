package net.stckoverflw.imaginize.utils

import com.mojang.blaze3d.platform.NativeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.resources.ResourceLocation
import net.silkmc.silk.core.task.mcClientCoroutineDispatcher
import net.silkmc.silk.core.task.mcCoroutineDispatcher
import net.stckoverflw.imaginize.config.SaveManager
import net.stckoverflw.imaginize.image.Image
import net.stckoverflw.imaginize.image.ImageManager
import java.io.IOException
import java.net.URL

private val regex = "[^a-z0-9/._\\-]".toRegex()

suspend fun addImage(link: String, position: Image.Position, client: Boolean = true): Image? {
    val nativeImage = try {
        if (client) {
            NativeImage.read(
                withContext(Dispatchers.IO) {
                    URL(link).openStream()
                }
            )
        } else null
    } catch (_: IOException) {
        return null
    }

    val resourceLocation =
        ResourceLocation(
            "screen-image",
            "${link.replace(regex, "").lowercase()}-${"${position.x}-${position.y}"}"
        )

    val image = Image(
        resourceLocation,
        link,
        position
    )

    withContext(if (client) mcClientCoroutineDispatcher else mcCoroutineDispatcher) {
        if (nativeImage != null) {
            Minecraft.getInstance()?.textureManager?.register(resourceLocation, DynamicTexture(nativeImage))
        }

        ImageManager.images.add(image)

        SaveManager.saveImagesToFile()
    }

    return image
}