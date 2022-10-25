package net.stckoverflw.imaginize.mixin;

import net.minecraft.client.Game;
import net.stckoverflw.imaginize.utils.ResetImagesKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Game.class)
public class GameMixin {

    @Inject(at = @At("HEAD"), method = "onStartGameSession()V")
    private void onStartGameSession(CallbackInfo ci) {
        ResetImagesKt.resetImages(true);
    }

}
