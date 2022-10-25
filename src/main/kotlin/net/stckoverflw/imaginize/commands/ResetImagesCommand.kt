package net.stckoverflw.imaginize.commands

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.chat.Component
import net.silkmc.silk.commands.clientCommand
import net.silkmc.silk.commands.sendSuccess
import net.stckoverflw.imaginize.packets.PacketChannel

fun resetImages() = clientCommand("reset-images", true) {
    runs {
        net.stckoverflw.imaginize.utils.resetImages(false)

        ClientPlayNetworking.send(
            PacketChannel.ResetImages, PacketByteBufs.empty()
        )

        source.sendSuccess(Component.literal("Reset images"))
    }
}