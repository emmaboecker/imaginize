package net.stckoverflw.minecraftttv.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Gui.class)
public interface GuiAccessor {
    @Invoker("renderTextureOverlay")
    void renderTextureOverlay(ResourceLocation resourceLocation, float f);
}
