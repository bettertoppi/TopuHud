package com.topuclient.topuhud.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HudConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("TopuHud Config");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private File configFile;
    private HudConfigData configData;

    public HudConfig() {
        File configDir = new File("config/topuhud");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        this.configFile = new File(configDir, "config.json");
        this.configData = new HudConfigData();
    }

    public void load() {
        try {
            if (configFile.exists()) {
                FileReader reader = new FileReader(configFile);
                configData = GSON.fromJson(reader, HudConfigData.class);
                reader.close();
                LOGGER.info("Config loaded from " + configFile.getAbsolutePath());
            } else {
                save();
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load config", e);
        }
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(configFile);
            GSON.toJson(configData, writer);
            writer.close();
            LOGGER.info("Config saved to " + configFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error("Failed to save config", e);
        }
    }

    public HudConfigData getConfigData() {
        return configData;
    }
}
