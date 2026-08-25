package com.topuclient.topuhud.client.hud.element;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import com.topuclient.topuhud.client.hud.HudElement;

public class ArmorStatusElement extends HudElement {
    public ArmorStatusElement(int x, int y) {
        super(x, y, "Armor Status");
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            PlayerEntity player = client.player;
            String armorInfo = "Armor: ";
            for (ItemStack armor : player.getArmorItems()) {
                if (!armor.isEmpty()) {
                    armorInfo += armor.getDamage() + " ";
                }
            }
            context.drawText(client.textRenderer, armorInfo, x, y, 0xFFFFFF, false);
        }
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
