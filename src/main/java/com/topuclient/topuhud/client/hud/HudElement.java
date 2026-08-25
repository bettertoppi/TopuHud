package com.topuclient.topuhud.client.hud;

import net.minecraft.client.gui.DrawContext;

public abstract class HudElement {
    protected int x;
    protected int y;
    protected boolean enabled = true;
    protected String name;

    public HudElement(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public abstract void render(DrawContext context, float tickDelta);

    public abstract void update(float tickDelta);

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public abstract int getWidth();

    public abstract int getHeight();
}
