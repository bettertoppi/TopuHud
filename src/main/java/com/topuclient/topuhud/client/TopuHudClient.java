package com.topuclient.topuhud.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.screen.Screen;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topuclient.topuhud.TopuHudMod;
import com.topuclient.topuhud.client.hud.HudManager;
import com.topuclient.topuhud.client.hud.HudRenderer;
import com.topuclient.topuhud.client.config.HudConfig;
import com.topuclient.topuhud.client.event.MouseClickListener;
import com.topuclient.topuhud.client.screen.HudEditScreen;
import com.topuclient.topuhud.client.screen.HudSettingsScreen;

@Environment(EnvType.CLIENT)
public class TopuHudClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopuHudMod.MOD_ID);

    public static KeyBinding toggleHudKey;
    public static KeyBinding editHudKey;
    public static KeyBinding settingsKey;

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

        settingsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.topuhud.settings",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_P,
            "category.topuhud.main"
        ));

        // Register HUD rendering
        HudRenderCallback.EVENT.register(new HudRenderer());

        // Register client tick event for keybindings and updates
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.isInSingleplayer() && client.isPaused()) {
                return;
            }

            // Update HUD elements
            hudManager.updateElements(0);

            // Toggle HUD visibility
            if (toggleHudKey.wasPressed()) {
                hudManager.toggleHud();
                LOGGER.info("HUD Toggled: " + hudManager.isHudEnabled());
            }

            // Edit HUD layout (R + Control)
            if (editHudKey.wasPressed() && (InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL) || 
                InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL))) {
                if (!hudManager.isEditMode()) {
                    hudManager.toggleEditMode();
                    client.setScreen(new HudEditScreen());
                    LOGGER.info("HUD Edit Mode Enabled");
                }
            }

            // Open settings
            if (settingsKey.wasPressed()) {
                client.setScreen(new HudSettingsScreen(client.currentScreen));
                LOGGER.info("Settings opened");
            }
        });

        // Register mouse click listener
        MouseClickListener.register();

        LOGGER.info("TopuHud keybindings and events registered!");
    }
}
