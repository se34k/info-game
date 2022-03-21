package com.infolk.game.core.interactions;

import java.util.ArrayList;

public class InteractionsManager {
    private ArrayList<Interaction> interactions;

    public InteractionsManager() {
        interactions = new ArrayList<>();
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }

    public void removeInteraction(Interaction interaction) {
        interactions.remove(interaction);
    }
}
