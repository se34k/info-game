package com.infolk.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import com.infolk.game.combat.Entity;
import com.infolk.game.combat.EntityObject;
import com.infolk.game.combat.Playable;

/**
 * @author Sebastian
 */
public class MapController {
    private Playable player;
    private ArrayList<Entity> entities;

    private String mapId;

    private Movement move;

    private TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    private AssetManager assetManager;

    public MapController(String mapId) {
        entities = new ArrayList<>();
        assetManager = new AssetManager();

        move = new Movement();

        this.mapId = mapId;

        loadMap(mapId);
    }

    private void loadMap(String mapId) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        
        assetManager.load("maps/maps2/" + mapId + ".tmx", TiledMap.class);
        assetManager.finishLoading();

        map = assetManager.get("maps/maps2/" + mapId + ".tmx");

        renderer = new OrthogonalTiledMapRenderer(map, 1f);
    }

    public String getMapId() {
        return mapId;
    }

    public void addEntity(Entity pEntity) {
        entities.add(pEntity);
    }

    public void removeEntity(Entity pEntity) {
        if (player == pEntity) {
            player = null;
        }
        entities.remove(pEntity);
    }

    public void addPlayer(Playable player) {
        addEntity(player);
        this.player = player;

        player.setPosition(100, 100);
    }

    public Playable getPlayer() {
        return player;
    }

    public void onLoop(float delta) {
        move.processKeys(player);

        // Testing
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            Rectangle hitboxAttack = new Rectangle(0,0,0,0);
            switch ((int) player.getVelocity().angleDeg()) {
                case 0:
                    hitboxAttack = new Rectangle(player.getX() + 30f, player.getY(), 50f, 50f);
                    break;
                case 90:
                    hitboxAttack = new Rectangle(player.getX(), player.getY() + 30f, 50f, 50f);
                    break;
                case 180:
                    hitboxAttack = new Rectangle(player.getX() - 30f, player.getY(), 50f, 50f);
                    break;
                case 270:
                    hitboxAttack = new Rectangle(player.getX(), player.getY() - 30f, 50f, 50f);
                    break;
            }

            ArrayList<Entity> overlaps = collisions(hitboxAttack, player);
            for (Entity entity : overlaps) {
                entity.changeHP(-1);
                if(entity.getHP() <= 0) {
                   entities.remove(entity);
                }
            }
        }
        //

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
        }
    }

    public ArrayList<Entity> collisions(Entity entity) {
        return collisions(entity.getHitbox(), entity);
    }

    public ArrayList<Entity> collisions(Rectangle shape, Entity entity) {
        ArrayList<Entity> violators = new ArrayList<>();

        for (Entity e : entities) {
            if (shape.overlaps(e.getHitbox()) && e != entity) {
                violators.add(e);
            }
        }


        MapLayer collisionLayer = map.getLayers().get("border");
        MapObjects objects = collisionLayer.getObjects();

        for (RectangleMapObject rmo : objects.getByType(RectangleMapObject.class)) {
            if (shape.overlaps(rmo.getRectangle())) {
                violators.add(new EntityObject("bullshit", new Sprite(), 10));
            }
        }
        
        return violators;
    }

    public void draw(SpriteBatch batch) {
        renderer.render();
        
        for (Entity entity : entities) {
            entity.draw(batch);
        }
    }
}
