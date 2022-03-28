package com.infolk.game.core.interactions;

import java.util.ArrayList;
import java.util.HashMap;

import com.infolk.game.App;
import com.infolk.game.core.interactions.InteractionCondition.ConditionType;

public class InteractionFactory {
    public enum TriggerType {
        INTERACT,
        KILLED,
        INCAPACITATED,
        SPAWN
    }

    private ArrayList<TriggerType> triggers;
    private ArrayList<InteractionCondition> conditions;
    private App app;

    public InteractionFactory() {
        triggers = new ArrayList<>();
        conditions = new ArrayList<>();
    }

    public void beginAssembly() {
        triggers.clear();
        conditions.clear();
    }

    public void addTrigger(TriggerType trigger) {
        triggers.add(trigger);
    }

    public void addCondition(ConditionType type, HashMap<String, Object> parameters) {
        conditions.add(new InteractionCondition(type, parameters));
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Interaction assemble() {
        return new Interaction(triggers, conditions, app);
    }
}
