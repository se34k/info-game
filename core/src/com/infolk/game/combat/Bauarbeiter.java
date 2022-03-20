package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bauarbeiter extends Enemy {
  public Bauarbeiter(float x, float y){
    super("Bauarbeiter", 40, new Sprite(new Texture(Gdx.files.internal("Bauarbeiter.png"))), x, y, 10f);
  }
   
  public void verfolgen(Entity entity){
    super.track(entity);
  }
}
