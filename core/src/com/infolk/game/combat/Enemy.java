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
    Vector2 location = new Vector2(player.getX(), player.getY());
    Vector2 coordinates = new Vector2(super.getX(), super.getY());
    Vector2 richtung = location.sub(coordinates); //Richtungsvektor zwischen Player und Gegner
    float x = 0;
    float y = 0;
    if (richtung.x < 0) {
      x = -1;
    } else if (richtung.x > 0) {
        x = 1;  
      }
    if (richtung.y < 0) {
      y = -1:;
    } else if (richtung.x > 0) {
        y = 1;  
      }  
    super.move(x, y);
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
