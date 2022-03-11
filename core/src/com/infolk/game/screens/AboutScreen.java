package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;

/**
 * @author Mihai
 */
public class AboutScreen extends DefaultScreen {

    public AboutScreen(final App app) {
        super();

        int width = (int) (Gdx.graphics.getWidth() * 0.6);
        int height = (int) (width * 0.2);
        addImage("gui/about.png", width, height, 0, 10);
        addText("Remind Mihai to implement this...", 0, 100);

        addButton("Back", 100, 100).addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                app.changeScreen("Menu");
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            }
        });
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void draw() {
    }
}