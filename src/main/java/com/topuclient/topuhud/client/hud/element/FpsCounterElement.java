package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;

public class FpsCounterElement extends HudElement {
    public FpsCounterElement(int x, int y) {
        super(x, y, "FPS Counter");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        String fpsText = "FPS: " + client.getCurrentFps();
        context.drawText(client.textRenderer, fpsText, x, y, 0xFFFFFF, false);
    }

    @Override
    public void update(float tickDelta) {
        // Update logic here
    }

    @Override
    public int getWidth() {
        return 80;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
