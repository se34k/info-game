package com.infolk.game.combat;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class DavyCrockett extends Entity {
    public DavyCrockett() {
        super("Davy Crockett Nuclear Device", 10, new Sprite(new Texture(Gdx.files.internal("sprites/davycrockett.png"))));

        setVelocity(new Vector2(50, 50));
        this.sprite.setRotation(this.getVelocity().angleDeg());
    }

    @Override
    public void onCollision(ArrayList<Entity> e) {
        detonate();
    }

    private void detonate() {
        // setVelocity(new Vector2(0, 0));
    }
}
