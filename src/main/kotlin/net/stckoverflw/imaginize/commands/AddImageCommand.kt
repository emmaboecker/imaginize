package net.stckoverflw.imaginize.commands

import kotlinx.coroutines.launch
import net.minecraft.network.chat.Component
import net.silkmc.silk.commands.clientCommand
import net.silkmc.silk.commands.sendFailure
import net.silkmc.silk.commands.sendSuccess
import net.stckoverflw.imaginize.coroutineScope
import net.stckoverflw.imaginize.image.Image
import net.stckoverflw.imaginize.image.ImageManager
import net.stckoverflw.imaginize.utils.addImage

private val line = 'a'..'z'

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

                coroutineScope.launch {
                    if (!addImage(linkArgument, Image.Position(positionX.toInt(), alphabetNumber))) {
                        source.sendFailure(Component.literal("unsupported image type"))
                    } else {
                        source.sendSuccess(Component.literal("Image added"))
                    }
                }
            }
        }
    }
}