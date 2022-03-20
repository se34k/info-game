package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;
public class Assi extends Enemy{
  
  public Assi(float x, float y){
    texture = new Texture(Gdx.files.internal("Assi.png"));
    Sprite sprite = new Sprite(texture);
    super.("Assi", 15, sprite, x, y, 20f);
  }
   
  public void verfolgen(Entity entity){
    super.track(entity);
  }
  
}
