package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.BlockPos;
import com.topuclient.topuhud.client.hud.HudElement;

public class CoordsElement extends HudElement {
    public CoordsElement(int x, int y) {
        super(x, y, "Coordinates");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            BlockPos pos = client.player.getBlockPos();
            String coords = String.format("XYZ: %d / %d / %d", pos.getX(), pos.getY(), pos.getZ());
            context.drawText(client.textRenderer, coords, x, y, 0xFFFFFF, false);
        }
    }

    @Override
    public void update(float tickDelta) {
        // Update logic here
    }

    @Override
    public int getWidth() {
        return 130;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
