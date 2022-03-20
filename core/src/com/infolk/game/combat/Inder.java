package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Inder extends NPC{
  
  public Inder(float x, float y){
    super("Inder", 1, new Sprite(new Texture(Gdx.files.internal("Inder.png"))), x, y);
  }
  
}