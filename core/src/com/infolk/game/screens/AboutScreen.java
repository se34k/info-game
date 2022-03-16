package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;

/**
 * @author Mihai
 */
public class AboutScreen extends DefaultScreen {

    public AboutScreen(final App app) {
        super();

        int width = (int) (Gdx.graphics.getWidth() * 0.6);
        int height = (int) (width * 0.2);
        addImage(mainTable, "gui/about.png", width, height, 0, 10, true);
        addText(mainTable, "Remind Mihai to implement this...", 0, 100, true);

        addButton(mainTable, "Back", 100, 100, 300, 75, true).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.changeScreen(ScreenState.MENU);
            }
        });
    }

    @Override
    public void draw() {
    }

    @Override
    public void cleanUp() {
    }

    @Override
    public void update(float delta) {
    }
}