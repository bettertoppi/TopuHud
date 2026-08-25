package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import com.topuclient.topuhud.client.hud.HudElement;

public class ParticleEffectElement extends HudElement {
    private int critParticleColor = 0xFFFF00; // Default yellow
    private boolean enabledParticles = true;

    public ParticleEffectElement(int x, int y) {
        super(x, y, "Particle Effects");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        String particleStatus = enabledParticles ? "Particles: ON" : "Particles: OFF";
        int color = enabledParticles ? 0x00FF00 : 0xFF0000;
        context.drawText(client.textRenderer, particleStatus, x, y, color, false);
    }

    @Override
    public void update(float tickDelta) {
        // Particle updates handled by event listeners
    }

    public void setCritParticleColor(int color) {
        this.critParticleColor = color;
    }

    public int getCritParticleColor() {
        return critParticleColor;
    }

    public void setParticlesEnabled(boolean enabled) {
        this.enabledParticles = enabled;
    }

    public boolean areParticlesEnabled() {
        return enabledParticles;
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
