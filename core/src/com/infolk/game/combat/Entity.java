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
}
