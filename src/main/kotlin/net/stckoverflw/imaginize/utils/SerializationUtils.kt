package net.stckoverflw.imaginize.utils

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.FriendlyByteBuf
import net.stckoverflw.imaginize.image.Image

fun FriendlyByteBuf.readLinkAndPosition(): Pair<String, Image.Position> {
    val length = readInt()

    val charSequence = readCharSequence(length, Charsets.UTF_8)
    val x = readInt()
    val y = readInt()

    return charSequence.toString() to Image.Position(x, y)
}

fun Image.serialize(): FriendlyByteBuf {
    val buf = PacketByteBufs.create()

    val byteArray = link.encodeToByteArray()

    buf.writeInt(byteArray.size)
    buf.writeBytes(byteArray)

    buf.writeInt(position.x)
    buf.writeInt(position.y)

    return FriendlyByteBuf(buf)
}
