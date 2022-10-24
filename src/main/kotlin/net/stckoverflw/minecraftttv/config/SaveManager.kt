package net.stckoverflw.minecraftttv.config

import com.mojang.blaze3d.platform.NativeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.stckoverflw.minecraftttv.coroutineScope
import net.stckoverflw.minecraftttv.image.ImageManager
import java.io.IOException
import java.net.URL
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

object SaveManager {

    private val json = Json {
        prettyPrint = true
    }

    private val configPath: Path
        get() {
            val path = FabricLoader.getInstance().configDir.resolve("screen-images/")
            if (!path.exists()) {
                path.createDirectories()
            }
            return path
        }

    fun saveImagesToFile() {
        val file = configPath.resolve("save.json")

        if (!file.exists()) {
            file.createFile()
        }

        file.writeText(
            json.encodeToString(
                ImageSave(
                    ImageManager.images,
                    ImageManager.grid
                )
            )
        )
    }

    private fun readSavesFromFile(): ImageSave? {
        val file = configPath.resolve("save.json")

        if (!file.exists() || file.readText().isEmpty()) {
            return null
        }

        return Json.decodeFromString(file.readText())
    }

    fun loadImagesSave() {
        coroutineScope.launch(Dispatchers.IO) {
            val save = readSavesFromFile()

            if (save != null) {
                ImageManager.grid = save.gridSize
                ImageManager.images.removeAll { true }

                save.images.forEach {
                    val nativeImage = try {
                        NativeImage.read(URL(it.link).openStream())
                    } catch (_: IOException) {
                        null
                    }
                    if (nativeImage != null) {
                        Minecraft.getInstance().textureManager.register(
                            it.resourceLocation,
                            DynamicTexture(nativeImage)
                        )
                        ImageManager.images.add(it)
                    }
                }
            }
        }
    }

}