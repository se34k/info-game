package com.infolk.game.combat;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    private Vector2 velocity;
    public Sprite sprite;

    private String name;
    private int hp;

    private Rectangle hitbox;

    protected Entity(String name, int hp, Sprite sprite) {
        this.name = name;
        this.hp = hp;

        //Initialize vector with 0, 0
        velocity = new Vector2(0, 0);

        this.sprite = sprite;
        this.sprite.setX(100f);
        this.sprite.setY(100f);

        hitbox = new Rectangle(getX(), getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean overlaps(Entity entity) {
        return hitbox.overlaps(entity.getHitbox());
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

    public void moveBack(Vector2 factor) {
        factor.x = factor.x * (velocity.x > 0 ? -1 : velocity.x < 0 ? 1 : 0);
        factor.y = factor.y * (velocity.y > 0 ? -1 : velocity.y < 0 ? 1 : 0);

        move(factor.x, factor.y);
    }

    public void move(float delta) {
        move(velocity.x * delta, velocity.y * delta);
    }

    public void moveHoriz(float delta) {
        move(velocity.x * delta, 0);
    }

    public void moveVert(float delta) {
        move(0, velocity.y * delta);
    }

    public void move(float x, float y) {
        setPosition(getX() + x, getY() + y);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        hitbox.setPosition(x, y);
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
