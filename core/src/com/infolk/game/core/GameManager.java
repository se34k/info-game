package com.infolk.game.core;

import java.util.ArrayList;

import com.infolk.game.core.interfaces.MapChangeListener;

/**
 * @author  Sebastian
 */
public class GameManager {
    private ArrayList<MapController> maps;
    private MapController currentMap;

    private ArrayList<MapChangeListener> mclisteners;

    public GameManager() {
        maps = new ArrayList<>();
        mclisteners = new ArrayList<>();
    }

    public void startGame() {
        String startMap = "halle";
        createAndAddMap(startMap);
        changeCurrentMap(startMap);
    }

    public void addMap(MapController map) {
        maps.add(map);
    }

    public MapController createAndAddMap(String mapId) {
        MapController map = new MapController(mapId);
        maps.add(map);

        return map;
    }

    public MapController getMap(String mapId) {
        for (MapController map : maps) {
            if (map.getMapId().equals(mapId)) {
                return map;
            }
        }

        return null;
    }

    public boolean mapExists(String mapId) {
        return getMap(mapId) != null;
    }

    public void discardMap(String mapId) {
        if (currentMap.getMapId().equals(mapId)) {
            currentMap = null;
        }
        maps.remove(getMap(mapId));
    }

    public void changeCurrentMap(String mapId) {
        if (getCurrentMap() != getMap(mapId)) {
            currentMap = getMap(mapId);

            for (MapChangeListener mcl : mclisteners) {
                mcl.onMapChange(currentMap);
            }
        }
    }

    public MapController getCurrentMap() {
        return currentMap;
    }

    public void registerMapChangeListener(MapChangeListener mcl) {
        mclisteners.add(mcl);
    }

    public void unregisterMapChangeListener(MapChangeListener mcl) {
        mclisteners.remove(mcl);
    }
}
