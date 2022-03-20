package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ninja extends Enemy{
  
  public Ninja(float x, float y){
    super("Ninja", 20, new Sprite(new Texture(Gdx.files.internal("Ninja.png"))), x, y, 40f);
  }
   
  public void verfolgen(Entity entity){
    track(entity);
  }
}
