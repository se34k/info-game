package com.infolk.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.infolk.game.combat.Entity;
import com.infolk.game.combat.Movement;
import com.infolk.game.combat.Playable;

/**
 * @author Sebastian
 */
public class MapController {
    private ArrayList<Playable> players;
    private ArrayList<Entity> entities;

    private Movement move;

    public MapController(String mapId) {
        players = new ArrayList<>();
        entities = new ArrayList<>();
    
		move = new Movement();

        //TODO: Load map data according to mapId parameter
    }

    public void addEntity(Entity pEntity) {
        entities.add(pEntity);
    }

    public void addPlayer(Playable player) {
        addEntity(player);
        players.add(player);
    }

    public void onLoop(float delta) {
        for (Playable player : players) {
            move.processKeys(player);
            player.move(delta);

            ArrayList<Entity> violators = collisions(player);
            if (violators.size() > 0) {
                for (Entity violator : violators) {
                    int tryc = 0;
                    while (player.overlaps(violator) && tryc < 1000) {
                        player.moveBack(new Vector2(1, 1));
                        tryc++;
                    }
                }
            }
        }
    }

    public ArrayList<Entity> collisions(Entity entity) {
        ArrayList<Entity> violators = new ArrayList<>();

        for (Entity e : entities) {
            if (e.overlaps(entity) && e != entity) {
                violators.add(e);
            }
        }

        return violators;
    }

    public void draw() {
        //TODO: Implement this and take it out of GameScreen
    }
}
