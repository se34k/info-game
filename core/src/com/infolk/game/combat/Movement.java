package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Movement {

    public Movement() {

    }

    public void processKeys(Entity entity, float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            entity.sprite.setPosition(entity.sprite.getX() - (entity.velocity * delta), entity.sprite.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            entity.sprite.setPosition(entity.sprite.getX() + (entity.velocity * delta), entity.sprite.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            entity.sprite.setPosition(entity.sprite.getX(), entity.sprite.getY() + (entity.velocity * delta));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            entity.sprite.setPosition(entity.sprite.getX(), entity.sprite.getY() - (entity.velocity * delta));
        }
    }

}
