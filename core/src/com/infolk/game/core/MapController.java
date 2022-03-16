package com.infolk.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.infolk.game.combat.DavyCrockett;
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

        // TODO: Load map data according to mapId parameter
    }

    public void addEntity(Entity pEntity) {
        entities.add(pEntity);
    }

    public void addPlayer(Playable player) {
        addEntity(player);
        players.add(player);
    }

    public void onLoop(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            DavyCrockett dc = new DavyCrockett();
            dc.setPosition(players.get(0).getX() + 50, players.get(0).getY() + 50);
            addEntity(dc);
        }

        for (Playable player : players) {
            move.processKeys(player);
        }

        for (Entity e : entities) {
            ArrayList<Entity> violators = collisions(e);
            if (!violators.isEmpty()) {
                e.onCollision(violators);
            }

            e.moveVert(delta);
            correctCollisions(e, 0, 1);

            e.moveHoriz(delta);
            correctCollisions(e, 1, 0);
        }
    }

    private void correctCollisions(Entity entity, int xFactor, int yFactor) {
        ArrayList<Entity> violators = collisions(entity);
        if (!violators.isEmpty()) {

            for (Entity violator : violators) {
                int tryc = 0;
                while (entity.overlaps(violator) && tryc < 1000) {
                    entity.moveBack(new Vector2(xFactor, yFactor));
                    tryc++;
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

    public void draw(SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.draw(batch);
        }
    }
}
