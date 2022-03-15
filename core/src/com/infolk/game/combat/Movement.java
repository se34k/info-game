package com.infolk.game.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Movement {

    private int KEY_LEFT;
    private int KEY_UP;
    private int KEY_RIGHT;
    private int KEY_DOWN;

    private static int SPEED = 100;

    public Movement() {
        KEY_LEFT = Input.Keys.A;
        KEY_UP = Input.Keys.W;
        KEY_RIGHT = Input.Keys.D;
        KEY_DOWN = Input.Keys.S;
    }

    public static float distanceBetween(Entity entity1, Entity entity2) {
        return entity1.distanceTo(entity2);
    }

    public void processKeys(Entity entity, float delta) {
        int xSpeed = 0;
        int ySpeed = 0;

        if (Gdx.input.isKeyPressed(KEY_LEFT)) {
            xSpeed -= SPEED;
        }
        if (Gdx.input.isKeyPressed(KEY_RIGHT)) {
            xSpeed += SPEED;
        }
        if (Gdx.input.isKeyPressed(KEY_UP)) {
            ySpeed += SPEED;
        }
        if (Gdx.input.isKeyPressed(KEY_DOWN)) {
            ySpeed -= SPEED;
        }

        entity.setVelocity(new Vector2(xSpeed, ySpeed));
    }
}
