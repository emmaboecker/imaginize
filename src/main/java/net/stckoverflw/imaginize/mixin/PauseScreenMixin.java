package net.stckoverflw.imaginize.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.stckoverflw.imaginize.ImaginizeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {

    protected PauseScreenMixin(Component component) {
        super(component);
    }

    private Component getText() {
        if (ImaginizeMod.Companion.getRenderImages()) {
            return Component.literal("Images shown");
        } else {
            return Component.literal("Images hidden");
        }
    }

    @Inject(at = @At("HEAD"), method = "createPauseMenu()V")
    private void createPauseMenu(CallbackInfo ci) {
        this.addRenderableWidget(new Button(this.width / 24, this.height / 24 + 24 - 16, 102, 20, getText(), button -> {
            ImaginizeMod.Companion.setRenderImages(!ImaginizeMod.Companion.getRenderImages());
            button.setMessage(getText());
        }));
    }

}
