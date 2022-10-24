package net.stckoverflw.imaginize.commands

import net.minecraft.network.chat.Component
import net.silkmc.silk.commands.clientCommand
import net.silkmc.silk.commands.sendSuccess
import net.stckoverflw.imaginize.image.ImageManager

fun resetImages() = clientCommand("reset-images", true) {
    runs {
        ImageManager.images.removeAll { true }

        source.sendSuccess(Component.literal("Reset images"))
    }
}