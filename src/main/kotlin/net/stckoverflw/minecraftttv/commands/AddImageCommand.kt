package net.stckoverflw.minecraftttv.commands

import com.mojang.blaze3d.platform.NativeImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.silkmc.silk.commands.clientCommand
import net.silkmc.silk.commands.sendFailure
import net.silkmc.silk.core.task.mcCoroutineDispatcher
import net.stckoverflw.minecraftttv.image.Image
import net.stckoverflw.minecraftttv.image.ImageManager
import java.io.IOException
import java.lang.Exception
import java.net.URL

private val line = 'a'..'z'

private val regex = "[^a-z0-9/._\\-]".toRegex()

private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

fun addImageCommand() = clientCommand("add-image", true) {
    argument<String>("position") { position ->
        argument("link") { link ->
            runs {
                val linkArgument: String = link()
                val positionArgument: String = position()

                val (positionY, positionX) = positionArgument.split("-")

                val alphabetNumber: Int = line.indexOf(positionY.lowercase()[0]) + 1

                if (alphabetNumber > ImageManager.grid.y) {
                    this.source.sendFailure(Component.literal("y is too high"))
                    return@runs
                }
                if (alphabetNumber < 1) {
                    this.source.sendFailure(Component.literal("not a valid character"))
                    return@runs
                }

                if (positionX.toInt() > ImageManager.grid.x) {
                    this.source.sendFailure(Component.literal("x is too high"))
                    return@runs
                }

                coroutineScope.launch(Dispatchers.IO) {
                    val nativeImage = try {
                        NativeImage.read(URL(linkArgument).openStream())
                    } catch (_: IOException) {
                        source.sendFailure(Component.literal("Unsupported Image type"))
                        return@launch
                    }

                    val resourceLocation =
                        ResourceLocation(
                            "screen-image",
                            "${linkArgument.replace(regex, "").lowercase()}-${positionArgument.lowercase()}"
                        )

                    withContext(mcCoroutineDispatcher) {
                        Minecraft.getInstance().textureManager.register(resourceLocation, DynamicTexture(nativeImage))

                        ImageManager.images.add(
                            Image(
                                resourceLocation,
                                Image.Position(
                                    positionX.toInt(),
                                    alphabetNumber
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}