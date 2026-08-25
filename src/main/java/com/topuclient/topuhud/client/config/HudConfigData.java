package com.topuclient.topuhud.client.config;

import java.util.HashMap;
import java.util.Map;

public class HudConfigData {
    public boolean hudEnabled = true;
    public Map<String, ElementConfig> elements = new HashMap<>();

    public HudConfigData() {
        // Default element configurations
        elements.put("armor", new ElementConfig(true, 10, 10));
        elements.put("clock", new ElementConfig(true, 10, 30));
        elements.put("direction", new ElementConfig(true, 10, 50));
        elements.put("coords", new ElementConfig(true, 10, 70));
        elements.put("memory", new ElementConfig(true, 10, 90));
        elements.put("fps", new ElementConfig(true, 10, 110));
        elements.put("cps", new ElementConfig(true, 10, 130));
        elements.put("combo", new ElementConfig(true, 10, 150));
        elements.put("modules", new ElementConfig(true, 10, 170));
    }

    public static class ElementConfig {
        public boolean enabled;
        public int x;
        public int y;
        public int color = 0xFFFFFF;
        public boolean showBackground = false;

        public ElementConfig(boolean enabled, int x, int y) {
            this.enabled = enabled;
            this.x = x;
            this.y = y;
        }
    }
}
