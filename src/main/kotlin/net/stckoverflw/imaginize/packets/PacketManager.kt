package net.stckoverflw.imaginize.packets

import kotlinx.coroutines.launch
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.FriendlyByteBuf
import net.stckoverflw.imaginize.coroutineScope
import net.stckoverflw.imaginize.image.ImageManager
import net.stckoverflw.imaginize.utils.addImage
import net.stckoverflw.imaginize.utils.changeGridSize
import net.stckoverflw.imaginize.utils.readLinkAndPosition
import net.stckoverflw.imaginize.utils.resetImages
import net.stckoverflw.imaginize.utils.serialize

object PacketManager {

    fun initializeServerSide() {
        ServerPlayNetworking.registerGlobalReceiver(PacketChannel.AddImage) { server, player, _, buf, _ ->
            val sendingBuf = buf.copy()

            val (link, position) = buf.readLinkAndPosition()

            coroutineScope.launch {
                addImage(link, position, false)

                server.playerList.players.filter { it.uuid != player.uuid }.forEach {
                    ServerPlayNetworking.send(
                        it, PacketChannel.AddImage, FriendlyByteBuf(sendingBuf)
                    )
                }
            }
        }
        ServerPlayNetworking.registerGlobalReceiver(PacketChannel.GridSize) { server, player, _, buf, _ ->
            server.playerList.players.filter { it.uuid != player.uuid }.forEach {
                ServerPlayNetworking.send(
                    it, PacketChannel.GridSize, FriendlyByteBuf(buf.copy())
                )
            }
        }
        ServerPlayNetworking.registerGlobalReceiver(PacketChannel.ResetImages) { server, player, _, _, _ ->
            resetImages()

            server.playerList.players.filter { it.uuid != player.uuid }.forEach {
                ServerPlayNetworking.send(
                    it, PacketChannel.ResetImages, PacketByteBufs.empty()
                )
            }
        }
        ServerPlayNetworking.registerGlobalReceiver(PacketChannel.RequestAllImages) { _, player, _, _, _ ->
            ServerPlayNetworking.send(
                player, PacketChannel.ResetImages, PacketByteBufs.empty()
            )
            ImageManager.images.forEach {
                val imageBuf = it.serialize()
                ServerPlayNetworking.send(
                    player, PacketChannel.AddImage, imageBuf
                )
            }
        }
    }

    fun initializeClientSide() {
        ClientPlayNetworking.registerGlobalReceiver(PacketChannel.AddImage) { _, _, buf, _ ->
            val (link, position) = buf.readLinkAndPosition()

            coroutineScope.launch {
                addImage(link, position)
            }
        }
        ClientPlayNetworking.registerGlobalReceiver(PacketChannel.ResetImages) { _, _, _, _ ->
            resetImages(false)
        }
        ClientPlayNetworking.registerGlobalReceiver(PacketChannel.GridSize) { _, _, buf, _ ->
            val newSize = buf.readInt()
            changeGridSize(newSize)
        }
    }

}