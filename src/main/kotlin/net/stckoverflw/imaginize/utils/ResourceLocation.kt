package net.stckoverflw.imaginize.utils

import net.minecraft.resources.ResourceLocation

const val MOD_ID = "imaginize"

fun String.resourceLocation() = ResourceLocation(MOD_ID, this)