package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;

//TODO: Make abstract
public class Entity implements RuntimeHook {
    public float velocity = 100.0f;
    public Sprite sprite;

    private String name;
    private int hp;

    public Entity(String name, int hp, Sprite sprite) {
        this.name = name;
        this.hp = hp;

        this.sprite = sprite;
        this.sprite.setX(100f);
        this.sprite.setY(100f);
    }

    public void handleLoopIteration() {
        if (velocity > 0) {
            velocity--;
        }
    }

    public String getName() {
        return name;
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

    public void move(float x, float y) {
        this.sprite.setPosition(getX() + x, getY() + y);
    }

    public void moveX(float x) {
        move(x, 0);
    }

    public void moveY(float y) {
        move(0, y);
    }

    public float getX() {
        return this.sprite.getX();
    }

    public float getY() {
        return this.sprite.getY();
    }

    public float[] getPos() {
        return new float[] { getX(), getY() };
    }

    /**
     * Can be used to calculate the distance between this entity and another given entity, for example
     * to use in the combat system when checking whether two opponents are near each other
     * 
     * @param entity    The entity to compare to
     * 
     * @return          A float value with the distance between the two entities
     */
    public float distanceTo(Entity entity) {
        //Pythagoras is our friend
        return (float) Math.sqrt(Math.pow(entity.getX() - getX(), 2) + Math.pow(entity.getY() - getY(), 2));
    }
}
