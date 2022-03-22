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

import com.infolk.game.combat.DavyCrockett;
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
        
        assetManager.load("maps/real/" + mapId + ".tmx", TiledMap.class);
        assetManager.finishLoading();

        map = assetManager.get("maps/real/" + mapId + ".tmx");

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
                // e.onCollision(violators);

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
                violators.add(new EntityObject("bullshit", new Sprite()));
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
