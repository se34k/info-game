package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;
public class Ratte extends Enemy{
  
  public Ratte(float x, float y){
    texture = new Texture(Gdx.files.internal("Ratte.png"));
    Sprite sprite = new Sprite(texture);
    super.("Ratte", 5, sprite, x, y, 30f);
  }
   
  public void verfolgen(Playable player, TiledMap tiledMap){
    super.track(player, tiledMap);
  }
  
}