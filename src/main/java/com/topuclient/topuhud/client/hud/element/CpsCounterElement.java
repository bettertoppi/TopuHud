package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;

public class CpsCounterElement extends HudElement {
    private int clickCount = 0;
    private long lastResetTime = System.currentTimeMillis();

    public CpsCounterElement(int x, int y) {
        super(x, y, "CPS Counter");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        String cpsText = "CPS: " + clickCount;
        context.drawText(client.textRenderer, cpsText, x, y, 0xFFFFFF, false);
    }

    @Override
    public void update(float tickDelta) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTime >= 1000) {
            clickCount = 0;
            lastResetTime = currentTime;
        }
    }

    public void recordClick() {
        clickCount++;
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
