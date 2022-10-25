package net.stckoverflw.imaginize.commands

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.FriendlyByteBuf
import net.silkmc.silk.commands.clientCommand
import net.stckoverflw.imaginize.packets.PacketChannel
import net.stckoverflw.imaginize.utils.changeGridSize

fun gridSize() = clientCommand("grid-size", true) {
    argument("newSize") { size ->
        runs {
            val newSize: Int = size()
            changeGridSize(newSize)

            val buf = PacketByteBufs.create()

            buf.writeInt(newSize)

            ClientPlayNetworking.send(
                PacketChannel.GridSize, FriendlyByteBuf(buf)
            )
        }
    }
}