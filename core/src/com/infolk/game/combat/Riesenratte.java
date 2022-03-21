package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Riesenratte extends Enemy {
    public Riesenratte(float x, float y){
        super("Riesenratte", 100, new Sprite(new Texture(Gdx.files.internal("Riesenratte.png"))), x, y, 20f);
    }
}
