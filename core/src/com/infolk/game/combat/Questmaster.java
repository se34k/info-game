package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Questmaster extends NPC{
    public Questmaster(float x, float y){
        super("Questmaster", 1, new Sprite(new Texture(Gdx.files.internal("Ratte.png"))), x, y);
    }
}
