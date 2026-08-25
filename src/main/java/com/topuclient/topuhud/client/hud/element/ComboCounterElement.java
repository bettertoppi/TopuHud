package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;

public class ComboCounterElement extends HudElement {
    private int comboCount = 0;
    private long lastHitTime = System.currentTimeMillis();

    public ComboCounterElement(int x, int y) {
        super(x, y, "Combo Counter");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        String comboText = "Combo: " + comboCount;
        context.drawText(client.textRenderer, comboText, x, y, 0xFFFFFF, false);
    }

    @Override
    public void update(float tickDelta) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime > 5000) {
            comboCount = 0;
        }
    }

    public void recordHit() {
        comboCount++;
        lastHitTime = System.currentTimeMillis();
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
