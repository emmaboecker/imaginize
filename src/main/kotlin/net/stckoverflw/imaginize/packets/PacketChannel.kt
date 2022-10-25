package net.stckoverflw.imaginize.packets

import net.stckoverflw.imaginize.utils.resourceLocation

object PacketChannel {
    val AddImage = "add_image_packet".resourceLocation()
    val ResetImages = "reset_images_packet".resourceLocation()

    val RequestAllImages = "request_all_images_packet".resourceLocation()

    val GridSize = "grid_size_packet".resourceLocation()
}
