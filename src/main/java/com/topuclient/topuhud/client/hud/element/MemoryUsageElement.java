package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;

public class MemoryUsageElement extends HudElement {
    public MemoryUsageElement(int x, int y) {
        super(x, y, "Memory Usage");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        long memUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
        long memMax = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        String memInfo = String.format("Memory: %dMB / %dMB", memUsed, memMax);
        context.drawText(client.textRenderer, memInfo, x, y, 0xFFFFFF, false);
    }

    @Override
    public void update(float tickDelta) {
        // Update logic here
    }

    @Override
    public int getWidth() {
        return 120;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
