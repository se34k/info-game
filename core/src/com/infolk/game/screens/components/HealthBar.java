package com.infolk.game.screens.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthBar {

    public float x, y, width, height, currentHealth, maxHealth;

    private ArrayList<Texture> hearts;

    private Texture heart = new Texture(Gdx.files.internal("healthbar/heart2.png"));
    private Texture border = new Texture(Gdx.files.internal("healthbar/border.png"));

    public HealthBar(float x, float y, float width, float height, float currentHealth, float maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;

        hearts = new ArrayList<>();

        update();
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < hearts.size(); i++) {
            batch.draw(hearts.get(i), x + (width / maxHealth * i), y, (width / maxHealth), height);
        }
    }

    public void update() {
        hearts.clear();

        for (int i = 0; i < maxHealth; i++) {
            if (i <= currentHealth) {
                hearts.add(heart);
            } else {
                hearts.add(border);
            }
        }
    }

    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
    }
}
