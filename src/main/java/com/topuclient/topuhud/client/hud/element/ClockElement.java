package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockElement extends HudElement {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ClockElement(int x, int y) {
        super(x, y, "Clock");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        String time = LocalTime.now().format(formatter);
        context.drawText(client.textRenderer, "Time: " + time, x, y, 0xFFFFFF, false);
    }

    @Override
    public void update(float tickDelta) {
        // Update logic here
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
