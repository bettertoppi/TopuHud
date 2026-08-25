package com.topuclient.topuhud.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ScrollableWidget;
import net.minecraft.text.Text;
import com.topuclient.topuhud.client.TopuHudClient;
import com.topuclient.topuhud.client.hud.HudElement;

@Environment(EnvType.CLIENT)
public class HudSettingsScreen extends Screen {
    private final Screen parent;
    private ScrollableWidget scrollWidget;

    public HudSettingsScreen(Screen parent) {
        super(Text.literal("TopuHud Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.clearChildren();
        int y = 30;

        // Title
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("TopuHud Settings"),
            button -> {}
        ).dimensions(this.width / 2 - 100, 10, 200, 20).build());

        // Element toggles
        for (HudElement element : TopuHudClient.hudManager.getHudElements()) {
            final HudElement elem = element;
            this.addDrawableChild(ButtonWidget.builder(
                Text.literal((elem.isEnabled() ? "✓ " : "✗ ") + elem.getName()),
                button -> {
                    elem.setEnabled(!elem.isEnabled());
                    this.init();
                }
            ).dimensions(20, y, 200, 20).build());
            y += 25;
        }

        // Back button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Back"),
            button -> this.close()
        ).dimensions(this.width / 2 - 50, this.height - 30, 100, 20).build());

        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
        TopuHudClient.hudConfig.save();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
