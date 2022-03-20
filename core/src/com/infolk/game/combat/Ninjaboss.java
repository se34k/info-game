package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
public class Ninjaboss extends Enemy{
  
  public Ninjaboss(float x, float y){
    super("Ninjaboss", 200, new Sprite(new Texture(Gdx.files.internal("Ninjaboss.png"))), x, y, 30f);
  }
   
  public void verfolgen(Entity entity){
    super.track(entity);
  }
  
}
