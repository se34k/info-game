package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Playable extends Entity {
    private int dashFactor;
    private float energy;

    public Playable(String name, int hp, Sprite sprite) {
        super(name, hp, sprite);

        dashFactor = 1;
        energy = 100f;
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
