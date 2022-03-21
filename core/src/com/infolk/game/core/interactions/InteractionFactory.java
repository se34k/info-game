package com.infolk.game.core.interactions;

import java.util.ArrayList;

public class InteractionFactory {
    public static enum TriggerType {
        
    }

    public ArrayList<TriggerType> triggers;
    public ArrayList<Enum> conditions;

    public InteractionFactory() {
        conditions = new ArrayList<>();
    }

    public void beginAssembly() {
        conditions.clear();
    }
}
