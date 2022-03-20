package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Movement {

    private int KEY_LEFT;
    private int KEY_UP;
    private int KEY_RIGHT;
    private int KEY_DOWN;
    private int KEY_DASH;

    public Movement() {
        KEY_LEFT = Input.Keys.A;
        KEY_UP = Input.Keys.W;
        KEY_RIGHT = Input.Keys.D;
        KEY_DOWN = Input.Keys.S;
        KEY_DASH = Input.Keys.SHIFT_LEFT;
    }

    public static float distanceBetween(Entity entity1, Entity entity2) {
        return entity1.distanceTo(entity2);
    }

    public void processKeys(Playable entity) {
        int xSpeed = 0;
        int ySpeed = 0;

        if (Gdx.input.isKeyPressed(KEY_LEFT)) {
            xSpeed -= 1;
        }
        if (Gdx.input.isKeyPressed(KEY_RIGHT)) {
            xSpeed += 1;
        }
        if (Gdx.input.isKeyPressed(KEY_UP)) {
            ySpeed += 1;
        }
        if (Gdx.input.isKeyPressed(KEY_DOWN)) {
            ySpeed -= 1;
        }

        if (Gdx.input.isKeyPressed(KEY_DASH)) {
            entity.setDashFactor(3);
        } else {
            entity.setDashFactor(1);
        }

        entity.setDirection(new Vector2(xSpeed, ySpeed));
    }
}
