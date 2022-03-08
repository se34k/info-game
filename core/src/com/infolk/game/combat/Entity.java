package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    public float velocity = 100.0f;
    public Sprite sprite;

    String name;
    int hp;

    public Entity(String name, int hp, Sprite sprite) {
        this.name = name;
        this.hp = hp;

        this.sprite = sprite;
        this.sprite.setX(100f);
        this.sprite.setY(100f);
    }

    public void move(float x, float y) {
        this.sprite.setPosition(getX() + x, getY() + y);
    }

    public void moveX(float x) {
        move(x, 0);
    }

    public void moveY(float y) {
        move(0, y);
    }

    public float getX() {
        return this.sprite.getX();
    }

    public float getY() {
        return this.sprite.getY();
    }
}
