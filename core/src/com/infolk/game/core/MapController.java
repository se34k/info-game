package com.infolk.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.infolk.game.combat.DavyCrockett;
import com.infolk.game.combat.Entity;
import com.infolk.game.combat.Movement;
import com.infolk.game.combat.Playable;

/**
 * @author Sebastian
 */
public class MapController {
    private Playable player;
    private ArrayList<Entity> entities;

    private String mapId;

    private Movement move;

    public MapController(String mapId) {
        entities = new ArrayList<>();

        move = new Movement();

        this.mapId = mapId;
        // TODO: Load map data according to mapId parameter
    }

    public String getMapId() {
        return mapId;
    }

    public void addEntity(Entity pEntity) {
        entities.add(pEntity);
    }

    public void addPlayer(Playable player) {
        addEntity(player);
        this.player = player;
    }

    public Playable getPlayer() {
        return player;
    }

    public void onLoop(float delta) {
        //Just for fun - this spawns a new Davy Crockett
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            DavyCrockett dc = new DavyCrockett();
            dc.setPosition(player.getX(), player.getY() + 50);
            addEntity(dc);
        }

        move.processKeys(player);

        for (Entity e : entities) {
            float cdelta = delta;

            ArrayList<Entity> violators = collisions(e);
            if (!violators.isEmpty()) {
                e.onCollision(violators);

                cdelta = 1; // Temporary fix - if object already has collided, setting cdelta to 1 will
                            // ensure one full step is checked as opposed to only a fraction of that -
                            // this will allow for a resolution of the collision in most cases
            }

            if (collisions(e.getProjected(cdelta, 1, 0), e).isEmpty()) {
                e.moveHoriz(delta);
            }
            if (collisions(e.getProjected(cdelta, 0, 1), e).isEmpty()) {
                e.moveVert(delta);
            }

            /*
             * correctCollisions(e, 0, 1, delta);
             * correctCollisions(e, 1, 0, delta);
             */
        }
    }

    private void correctCollisions(Entity entity, int xFactor, int yFactor, float delta) {
        ArrayList<Entity> violators = collisions(entity);
        if (!violators.isEmpty()) {

            for (Entity violator : violators) {
                int tryx = 0;
                while (entity.overlaps(violator) && entity.getPosition().x != entity
                        .getProjected(delta, (int) entity.getDirection().x * -1, (int) entity.getDirection().y * -1)
                        .getX()) {
                    entity.moveBack(new Vector2(xFactor, yFactor));
                    tryx += xFactor;
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

    public ArrayList<Entity> collisions(Rectangle shape, Entity entity) {
        ArrayList<Entity> violators = new ArrayList<>();

        for (Entity e : entities) {
            if (shape.overlaps(e.getHitbox()) && e != entity) {
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
