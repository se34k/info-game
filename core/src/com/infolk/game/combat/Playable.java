package com.infolk.game.combat;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Playable extends Entity {
    private int dashFactor;
    private float energy;


    // Not working
        private int numberOfBooks;
        private ArrayList<Sprite> books;
    //
    public Playable(String name, int hp, Sprite sprite) {
        this(name, hp, sprite, 0, 0);

        numberOfBooks = 5;
        books = new ArrayList<>();
        addBooks();
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
        numberOfBooks = 5;
        books = new ArrayList<>();   
        addBooks();
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

    // Not working
        public void addBooks() {
            for(int i = 0; i < numberOfBooks; i++) {
                books.add(new Sprite(new Texture(Gdx.files.internal("sprites/Buch.png"))));
            }
        }

        public void throwBook(ArrayList<Entity> entities) {
            if(books.size() > 0) {
            int deg = (int) getVelocity().angleDeg();
            float pX = getX();
            float pY = getY();

            if(deg == 0) {

            }
            
            }
        }
    //
}
