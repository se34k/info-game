package com.infolk.game.screens.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthBar {

    public float x, y, width, height, currentHealth, maxHealth;

    private ArrayList<Texture> hearts;

    public HealthBar(float x, float y, float width, float height, float currentHealth, float maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;

        hearts = new ArrayList<>();

        for (int i = 0; i < maxHealth; i++) {
            if (i <= currentHealth) {
                hearts.add(new Texture(Gdx.files.internal("healthbar/heart.png")));
            } else {
                hearts.add(new Texture(Gdx.files.internal("healthbar/border.png")));
            }

        }
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < hearts.size(); i++) {
            batch.draw(hearts.get(i), x + (width / maxHealth * i) - (int) (width / maxHealth * 0.5), y,
                    (width / maxHealth),
                    height);
        }
    }

    public void update() {
        hearts.clear();
        for (int i = 0; i < maxHealth; i++) {
            if (i <= currentHealth) {
                hearts.add(new Texture(Gdx.files.internal("healthbar/heart.png")));
            } else {
                hearts.add(new Texture(Gdx.files.internal("healthbar/border.png")));
            }

        }
    }
}
