package net.stckoverflw.imaginize.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.stckoverflw.imaginize.config.SaveManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/main/GameConfig;)V")
    private void init(GameConfig gameConfig, CallbackInfo ci) {
        SaveManager.INSTANCE.loadImagesSave();
    }

}
