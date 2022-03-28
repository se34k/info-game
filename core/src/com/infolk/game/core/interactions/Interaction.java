package com.infolk.game.core.interactions;

import java.util.ArrayList;

import com.infolk.game.App;
import com.infolk.game.core.interactions.InteractionFactory.TriggerType;

public class Interaction {
    private ArrayList<TriggerType> triggers;
    private ArrayList<InteractionCondition> conditions;
    private App app;

    public Interaction(ArrayList<TriggerType> triggers, ArrayList<InteractionCondition> conditions, App app) {
        this.triggers = triggers;
        this.conditions = conditions;
        this.app = app;
    }

    public boolean checkConditions() {
        for (InteractionCondition condition : conditions) {
            if (!checkCondition(condition)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkCondition(InteractionCondition condition) {
        switch (condition.getType()) {
            case INVENTORY_CONTAINS:
                app.getGameManager().getInventory();
                break;
        }
        return false;
    }
}
