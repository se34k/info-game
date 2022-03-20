package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Entity {

  private boolean isDead;
  private boolean isAttackable;
  private float reach;

  protected Enemy(String name, int hp, Sprite sprite, float x, float y, float reach) {
    super(name, hp, sprite, x, y);
    isDead = false;
    isAttackable = false;
    this.reach = reach;
  }

  public void track(Entity player, TiledMap tiledMap) {
    if (!isInAttackreach(player)) {
      return;
    }

    Vector2 location = new Vector2(player.getX(), player.getY());
    Vector2 coordinates = new Vector2(getX(), getY());
    Vector2 richtung = location.sub(coordinates); // Richtungsvektor zwischen Player und Gegner
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

    move(x, y);
  }

  public boolean isDead() {
    return isDead;
  }

  public boolean isAttackable() {
    return isAttackable;
  }

  public boolean isInAttackreach(Entity player) {
    return (distanceTo(player) <= reach);
  }
}
