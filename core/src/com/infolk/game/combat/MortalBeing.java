package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.infolk.game.screens.components.HealthBar;

public class MortalBeing extends Entity {
    private int hp;

    private HealthBar bar;
    private float barWidth, barHeight;
    public boolean displayBar = true;

    public MortalBeing(String name, int hp, Sprite sprite) {
        super(name, sprite);

        this.hp = hp;

        barWidth = getSprite().getWidth();
        barHeight = barWidth / 5;

        bar = new HealthBar(getBarX(), getBarY(), barWidth, barHeight, hp, hp);
    }

    public MortalBeing(String name, int hp, Sprite sprite, float x, float y) {
        this(name, hp, sprite);
        setPosition(x, y);

        this.hp = hp;
    }

    private float getBarX() {
        return getSprite().getX() + getSprite().getWidth() / 2 - barWidth / 2;
    }

    private float getBarY() {
        return getSprite().getY() + getSprite().getHeight();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        
        if (displayBar) {
            bar.x = getBarX();
            bar.y = getBarY();
            bar.update();
            bar.draw(batch);
        }
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void changeHP(int change) {
        setHP(hp + change);
    }
}
