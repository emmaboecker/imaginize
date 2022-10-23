package net.stckoverflw.minecraftttv.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.stckoverflw.minecraftttv.image.ImageManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin extends GuiComponent {

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;F)V",
            at = @At("HEAD")
    )
    private void renderInjection(PoseStack _poseStack, float f, CallbackInfo ci) {
        ImageManager.INSTANCE.renderImages(f);
    }



}


