package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Endboss extends Enemy {
    public Endboss(float x, float y) {
        super("Endboss", 500, new Sprite(new Texture(Gdx.files.internal("Endboss.png"))), x, y, 30f);
    }
    
    public void verfolgen(Entity entity) {
        super.track(entity);
    }
}
