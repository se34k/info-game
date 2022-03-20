package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class EntityObject extends Entity {
    public EntityObject(String name, Sprite sprite) {
        super(name, sprite);
    }

    public EntityObject(String name, Sprite sprite, float x, float y) {
        super(name, sprite, x, y);
    }
}
