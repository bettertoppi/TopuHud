package com.topuclient.topuhud.client.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import com.topuclient.topuhud.client.TopuHudClient;
import com.topuclient.topuhud.client.hud.element.CpsCounterElement;
import com.topuclient.topuhud.client.hud.element.ComboCounterElement;

@Environment(EnvType.CLIENT)
public class MouseClickListener {
    private static boolean wasAttackPressed = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            boolean isAttackPressed = client.options.attackKey.isPressed();

            if (isAttackPressed && !wasAttackPressed) {
                recordClick();
            }

            wasAttackPressed = isAttackPressed;
        });
    }

    private static void recordClick() {
        for (var element : TopuHudClient.hudManager.getHudElements()) {
            if (element instanceof CpsCounterElement) {
                ((CpsCounterElement) element).recordClick();
            }
            if (element instanceof ComboCounterElement) {
                ((ComboCounterElement) element).recordHit();
            }
        }
    }
}
