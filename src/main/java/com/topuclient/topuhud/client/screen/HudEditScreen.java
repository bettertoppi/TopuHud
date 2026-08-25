package com.topuclient.topuhud.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import com.topuclient.topuhud.client.TopuHudClient;
import com.topuclient.topuhud.client.hud.HudElement;

@Environment(EnvType.CLIENT)
public class HudEditScreen extends Screen {
    private HudElement selectedElement = null;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;

    public HudEditScreen() {
        super(Text.literal("Edit HUD Layout"));
    }

    @Override
    protected void init() {
        this.clearChildren();
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Done"),
            button -> this.close()
        ).dimensions(this.width / 2 - 50, this.height - 30, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        // Render HUD elements with selection boxes
        for (HudElement element : TopuHudClient.hudManager.getHudElements()) {
            if (element.isEnabled()) {
                element.render(context, delta);
                // Draw selection box around element
                if (element == selectedElement) {
                    context.drawBorder(
                        element.getX() - 2,
                        element.getY() - 2,
                        element.getWidth() + 4,
                        element.getHeight() + 4,
                        0xFF00FF00
                    );
                }
            }
        }

        context.drawCenteredTextWithShadow(this.textRenderer, "Drag to move elements", this.width / 2, 10, 0xFFFFFF);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (HudElement element : TopuHudClient.hudManager.getHudElements()) {
            if (element.isEnabled() &&
                mouseX >= element.getX() && mouseX < element.getX() + element.getWidth() &&
                mouseY >= element.getY() && mouseY < element.getY() + element.getHeight()) {
                selectedElement = element;
                dragOffsetX = (int) mouseX - element.getX();
                dragOffsetY = (int) mouseY - element.getY();
                return true;
            }
        }
        selectedElement = null;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (selectedElement != null && button == 0) {
            int newX = (int) mouseX - dragOffsetX;
            int newY = (int) mouseY - dragOffsetY;
            selectedElement.setPosition(newX, newY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public void close() {
        this.client.setScreen(null);
        TopuHudClient.hudManager.toggleEditMode();
        TopuHudClient.hudConfig.save();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
