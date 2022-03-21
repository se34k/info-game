package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shopkeeper extends NPC {
    public Shopkeeper(float x, float y) {
        super("Shopkeeper", 1, new Sprite(new Texture(Gdx.files.internal("Shopkeeper.png"))), x, y);
    }
}