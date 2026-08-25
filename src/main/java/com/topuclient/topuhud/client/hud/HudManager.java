package com.topuclient.topuhud.client.hud;

import java.util.ArrayList;
import java.util.List;

import com.topuclient.topuhud.client.hud.element.*;

public class HudManager {
    private List<HudElement> hudElements;
    private boolean hudEnabled = true;
    private boolean editMode = false;

    public HudManager() {
        hudElements = new ArrayList<>();
        initializeElements();
    }

    private void initializeElements() {
        hudElements.add(new ArmorStatusElement(10, 10));
        hudElements.add(new ClockElement(10, 30));
        hudElements.add(new DirectionElement(10, 50));
        hudElements.add(new CoordsElement(10, 70));
        hudElements.add(new MemoryUsageElement(10, 90));
        hudElements.add(new FpsCounterElement(10, 110));
        hudElements.add(new CpsCounterElement(10, 130));
        hudElements.add(new ComboCounterElement(10, 150));
        hudElements.add(new ModuleDisplayElement(10, 170));
    }

    public void toggleHud() {
        hudEnabled = !hudEnabled;
    }

    public void toggleEditMode() {
        editMode = !editMode;
    }

    public List<HudElement> getHudElements() {
        return hudElements;
    }

    public boolean isHudEnabled() {
        return hudEnabled;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void updateElements(float tickDelta) {
        for (HudElement element : hudElements) {
            if (element.isEnabled()) {
                element.update(tickDelta);
            }
        }
    }
}
