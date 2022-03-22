package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class EntityObject extends Entity {
    public EntityObject(String name, Sprite sprite, int hp) {
        super(name, hp, sprite);
    }

    public EntityObject(String name, Sprite sprite, float x, float y, int hp) {
        super(name, hp, sprite, x, y);
    }
}
