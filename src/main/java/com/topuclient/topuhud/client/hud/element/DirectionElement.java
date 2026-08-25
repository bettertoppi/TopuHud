package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;

public class DirectionElement extends HudElement {
    public DirectionElement(int x, int y) {
        super(x, y, "Direction");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            float yaw = client.player.getYaw();
            String direction = getDirection(yaw);
            context.drawText(client.textRenderer, "Direction: " + direction + " (" + String.format("%.1f", yaw) + "°)", x, y, 0xFFFFFF, false);
        }
    }

    private String getDirection(float yaw) {
        yaw = yaw % 360;
        if (yaw < 0) yaw += 360;

        if (yaw < 45 || yaw >= 315) return "S";
        if (yaw < 135) return "W";
        if (yaw < 225) return "N";
        return "E";
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
