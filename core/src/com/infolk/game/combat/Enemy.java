package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;
public abstract class Enemy extends Entity{
  
  boolean isDead;
  boolean isAttackable;
  float reach;
  
  public Enemy(String name, int hp, Sprite sprite, float x, float y, float reach){
    super(name, hp, sprite, x, y);
    isDead = false;
    isAttackable = false;
    this.reach = reach;
  }
  
  public void track(Playable player, TiledMap tiledMap){
    if(this.isInAttackreach(player) == false){
      return;
    }
    
  }
  
  public boolean isDead(){
    return isDead;
  }
  
  public boolean isAttackable(){
    return isAttackable;
  }
  
  public boolean isInAttackreach(Playable player){
    if(super.distanceto(player) <= reach){
      return true;
    }
    return false;
  }
  
}