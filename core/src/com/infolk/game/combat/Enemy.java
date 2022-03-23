package com.infolk.game.combat;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Enemy extends NPC {

  private boolean isAttackable;
  private float reach;

  protected Enemy(String name, int hp, Sprite sprite, float x, float y, float reach) {
    super(name, hp, sprite, x, y);
    isAttackable = false;
    this.reach = reach;
  }

  public void track(Entity player) {
    if (!isInAttackReach(player)) {
      return;
    }
    
    Vector2 richtung = vectorTo(player); // Richtungsvektor zwischen Player und Gegner

    float x = 0;
    float y = 0;

    if (richtung.x < 0) {
      x = -1;
    } else if (richtung.x > 0) {
      x = 1;
    }

    if (richtung.y < 0) {
      y = -1;
    } else if (richtung.x > 0) {
      y = 1;
    }
    setDirection(richtung);
    //move(x, y);
  }

  public boolean isAttackable() {
    return isAttackable;
  }
  
  public boolean isInAttackReach(Entity player) {
    return (distanceTo(player) <= reach);
  }
  
  public void setReach(float reach){
    this.reach = reach;  
  }
}

