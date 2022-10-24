package net.stckoverflw.imaginize.image

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import java.util.Collections

object ImageManager {

    var grid = Grid(12, 6)

    val images: MutableList<Image> = Collections.synchronizedList(mutableListOf<Image>())

    fun renderImages(f: Float) {
        val screenWidth = Minecraft.getInstance().window.guiScaledWidth.toDouble()
        val screenHeight = Minecraft.getInstance().window.guiScaledHeight.toDouble()

        val (x1, y1) = grid

        val tileWidth = screenWidth / x1.toDouble()
        val tileHeight = screenHeight / y1.toDouble()


        images.forEach { (resourceLocation, _, position): Image ->
            val x = position.x.toDouble() - 1
            val y = position.y.toDouble() - 1
            renderImage(
                resourceLocation,
                tileWidth * x,
                tileHeight * (y + 1.0),
                tileWidth * (x + 1.0),
                tileHeight * (y + 1.0),
                tileWidth * (x + 1.0),
                tileHeight * y,
                tileWidth * x,
                tileHeight * y,
                f
            )
        }
    }

    private fun renderImage(
        resource: ResourceLocation,
        x1: Double,
        y1: Double,
        x2: Double,
        y2: Double,
        x3: Double,
        y3: Double,
        x4: Double,
        y4: Double,
        abc: Float
    ) {
        RenderSystem.disableDepthTest()
        RenderSystem.depthMask(false)
        RenderSystem.defaultBlendFunc()
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, abc)
        RenderSystem.setShaderTexture(0, resource)
        val tesselator = Tesselator.getInstance()
        val bufferBuilder = tesselator.builder
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX)
        bufferBuilder.vertex(x1, y1, -90.0).uv(0.0f, 1.0f).endVertex()
        bufferBuilder.vertex(x2, y2, -90.0).uv(1.0f, 1.0f).endVertex()
        bufferBuilder.vertex(x3, y3, -90.0).uv(1.0f, 0.0f).endVertex()
        bufferBuilder.vertex(x4, y4, -90.0).uv(0.0f, 0.0f).endVertex()
        tesselator.end()
        RenderSystem.depthMask(true)
        RenderSystem.enableDepthTest()
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
    }
}