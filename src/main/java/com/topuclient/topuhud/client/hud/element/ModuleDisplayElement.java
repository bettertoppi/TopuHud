package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.topuclient.topuhud.client.hud.HudElement;
import java.util.ArrayList;
import java.util.List;

public class ModuleDisplayElement extends HudElement {
    private List<String> activeModules = new ArrayList<>();

    public ModuleDisplayElement(int x, int y) {
        super(x, y, "Module Display");
        initializeModules();
    }

    private void initializeModules() {
        activeModules.add("[Armor]");
        activeModules.add("[Clock]");
        activeModules.add("[Coords]");
        activeModules.add("[FPS]");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        String modulesText = String.join(" ", activeModules);
        context.drawText(client.textRenderer, "Active: " + modulesText, x, y, 0x00FF00, false);
    }

    @Override
    public void update(float tickDelta) {
        // Update active modules based on their enabled state
    }

    @Override
    public int getWidth() {
        return 250;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
