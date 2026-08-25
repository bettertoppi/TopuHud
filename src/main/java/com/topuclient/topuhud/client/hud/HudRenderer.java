package com.topuclient.topuhud.client.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.TopuHudClient;

@Environment(EnvType.CLIENT)
public class HudRenderer implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (!TopuHudClient.hudManager.isHudEnabled()) {
            return;
        }

        for (HudElement element : TopuHudClient.hudManager.getHudElements()) {
            if (element.isEnabled()) {
                element.render(drawContext, tickDelta);
            }
        }
    }
}
