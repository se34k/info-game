package com.infolk.game;

import java.util.ArrayList;

import com.infolk.game.core.MapController;

public class GameManager {
    private ArrayList<MapController> maps;

    public GameManager() {
        maps = new ArrayList<>();
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
        maps.remove(getMap(mapId));
    }
}
