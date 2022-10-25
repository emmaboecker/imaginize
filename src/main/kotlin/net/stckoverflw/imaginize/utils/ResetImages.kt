package net.stckoverflw.imaginize.utils

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.stckoverflw.imaginize.image.ImageManager
import net.stckoverflw.imaginize.packets.PacketChannel

fun resetImages(refetchFromServer: Boolean = false) {
    ImageManager.images.removeAll { true }

    if (refetchFromServer) {
        ClientPlayNetworking.send(
            PacketChannel.RequestAllImages, PacketByteBufs.empty()
        )
    }
}