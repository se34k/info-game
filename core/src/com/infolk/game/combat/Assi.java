package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
public class Assi extends Enemy {
  
  public Assi(float x, float y){
    super("Assi", 15, new Sprite(new Texture(Gdx.files.internal("Assi.png"))), x, y, 20f);
  }
   
  public void verfolgen(Entity entity, TiledMap tiledMap){
    track(entity, tiledMap);
  }
}