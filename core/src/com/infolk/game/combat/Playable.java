package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Playable extends MortalBeing {
    private int dashFactor;
    private float energy;

    public Playable(String name, int hp, Sprite sprite) {
        this(name, hp, sprite, 0, 0);
    }

    public Playable(String name, int hp, Sprite sprite, float x, float y) {
        super(name, hp, sprite, x, y);

        getSprite().setSize(getSprite().getWidth() / 4, getSprite().getHeight() / 4);
        super.adjustHitbox();

        dashFactor = 1;
        energy = 100f;
        super.displayBar = false;
        setPosition(20, 20);
        setSpeed(50);
    }

    @Override
    public void move(float delta) {
        if (dashFactor == 1) {
            if (energy < 100 - delta) {
                energy += delta;
            } else {
                energy = 100;
            }
        } else {
            if (energy > 0 + delta) {
                energy -= delta;
            } else {
                energy = 0;
            }
        }
        super.move(delta * dashFactor);
    }

    public void setDashFactor(int df) {
        if (energy > 0) {
            dashFactor = df;
        } else {
            dashFactor = 1;
        }
    }
}
