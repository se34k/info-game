package com.infolk.game.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;

public class InventoryScreen extends DefaultScreen {

    public InventoryScreen(final App app) {
        super();

        TextButton menuBtn = addButton(mainTable, "Menu", 0, 0, true);
        TextButton backBtn = addButton(mainTable, "Back", 0, 0, true);

        menuBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.changeScreen(ScreenState.MENU);
            }
        });

        backBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.changeScreen(ScreenState.START);
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
