package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Movement {

    private int KEY_LEFT;
    private int KEY_UP;
    private int KEY_RIGHT;
    private int KEY_DOWN;

    public Movement() {
        KEY_LEFT = Input.Keys.A;
        KEY_UP = Input.Keys.W;
        KEY_RIGHT = Input.Keys.D;
        KEY_DOWN = Input.Keys.S;
    }

    public void processKeys(Entity entity, float delta) {
        if(Gdx.input.isKeyPressed(KEY_LEFT)) {
            entity.moveX(entity.velocity * delta);
        }
        if(Gdx.input.isKeyPressed(KEY_RIGHT)) {
            entity.moveX(entity.velocity * delta);
        }
        if(Gdx.input.isKeyPressed(KEY_UP)) {
            entity.moveY(entity.velocity * delta);
        }
        if(Gdx.input.isKeyPressed(KEY_DOWN)) {
            entity.moveY(entity.velocity * delta);
        }
    }
}
