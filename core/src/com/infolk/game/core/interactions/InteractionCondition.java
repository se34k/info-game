package com.infolk.game.core.interactions;

import java.util.HashMap;

public class InteractionCondition {
    public enum ConditionType {
        CHAPTER_ACTIVE,
        MAP_ACTIVE,
        ON_MAP,
        INVENTORY_CONTAINS,
        DEAD,
        ALIVE,
        PLAYER_ALIVE
    }

    private ConditionType conditionType;
    private HashMap<String, Object> conditionParameters;

    public InteractionCondition(ConditionType type, HashMap<String, Object> parameters) {
        conditionType = type;
        conditionParameters = parameters;
    }

    public ConditionType getType() {
        return conditionType;
    }

    public HashMap<String, Object> getParameters() {
        return conditionParameters;
    }
}
