package com.topuclient.topuhud;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopuHudMod implements ModInitializer {
    public static final String MOD_ID = "topuhud";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("TopuHud mod initialized!");
    }
}
