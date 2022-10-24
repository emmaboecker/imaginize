package net.stckoverflw.minecraftttv.utils

import com.mojang.blaze3d.platform.NativeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.resources.ResourceLocation
import net.silkmc.silk.core.task.mcCoroutineDispatcher
import net.stckoverflw.minecraftttv.config.SaveManager
import net.stckoverflw.minecraftttv.image.Image
import net.stckoverflw.minecraftttv.image.ImageManager
import java.io.IOException
import java.net.URL

private val regex = "[^a-z0-9/._\\-]".toRegex()

suspend fun addImage(link: String, position: Image.Position): Boolean {
    val nativeImage = try {
        NativeImage.read(
            withContext(Dispatchers.IO) {
                URL(link).openStream()
            }
        )
    } catch (_: IOException) {
        return false
    }

    val resourceLocation =
        ResourceLocation(
            "screen-image",
            "${link.replace(regex, "").lowercase()}-${"${position.x}-${position.y}"}"
        )

    withContext(mcCoroutineDispatcher) {
        Minecraft.getInstance().textureManager.register(resourceLocation, DynamicTexture(nativeImage))

        ImageManager.images.add(
            Image(
                resourceLocation,
                link,
                position
            )
        )

        SaveManager.saveImagesToFile()
    }
    return true
}