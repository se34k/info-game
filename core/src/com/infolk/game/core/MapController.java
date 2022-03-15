package com.infolk.game.core;

import java.util.ArrayList;

import com.infolk.game.combat.Entity;
import com.infolk.game.combat.Playable;

public class MapController {
    private ArrayList<Playable> players;
    private ArrayList<Entity> entities;

    public MapController() {
        players = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public void addEntity(Entity pEntity) {
        entities.add(pEntity);
    }
}
