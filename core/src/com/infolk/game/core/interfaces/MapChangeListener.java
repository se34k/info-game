package com.infolk.game.core.interfaces;

import com.infolk.game.core.MapController;

public interface MapChangeListener {
    /**
     * This method gets called when the current map changes
     * 
     * @param newMap        The new current MapController
     */
    public void onMapChange(MapController newMap);
}
