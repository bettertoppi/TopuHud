package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import com.topuclient.topuhud.client.hud.HudElement;
import java.util.List;

public class PlayerTagElement extends HudElement {
    public PlayerTagElement(int x, int y) {
        super(x, y, "Player Tags");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return;

        String clientName = client.player != null ? client.player.getName().getString() : "Unknown";
        String displayText = "tc_" + clientName + " | TopuClient";
        context.drawText(client.textRenderer, displayText, x, y, 0x00AAFF, false);
    }

    @Override
    public void update(float tickDelta) {
        // Updates handled in render
    }

    @Override
    public int getWidth() {
        return 150;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
