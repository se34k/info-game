package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
public class Ratte extends Enemy {
  
    public Ratte(float x, float y){
        super("Ratte", 5, new Sprite(new Texture(Gdx.files.internal("Ratte.png"))), x, y, 30f);
    }
   
    public void verfolgen(Playable player, TiledMap tiledMap){
        track(player, tiledMap);
    }
}