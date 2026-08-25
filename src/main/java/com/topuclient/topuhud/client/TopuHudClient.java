package com.topuclient.topuhud.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topuclient.topuhud.TopuHudMod;
import com.topuclient.topuhud.client.hud.HudManager;
import com.topuclient.topuhud.client.config.HudConfig;

@Environment(EnvType.CLIENT)
public class TopuHudClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopuHudMod.MOD_ID);

    public static KeyBinding toggleHudKey;
    public static KeyBinding editHudKey;

    public static HudManager hudManager;
    public static HudConfig hudConfig;

    @Override
    public void onInitializeClient() {
        LOGGER.info("TopuHud client initialized!");

        // Initialize configuration
        hudConfig = new HudConfig();
        hudConfig.load();

        // Initialize HUD manager
        hudManager = new HudManager();

        // Register keybindings
        toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.topuhud.toggle_hud",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.topuhud.main"
        ));

        editHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.topuhud.edit_hud",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.topuhud.main"
        ));

        // Register client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleHudKey.wasPressed()) {
                hudManager.toggleHud();
            }
            if (editHudKey.wasPressed() && client.options.sneakKey.isPressed()) {
                hudManager.toggleEditMode();
            }
        });

        LOGGER.info("TopuHud keybindings registered!");
    }
}
