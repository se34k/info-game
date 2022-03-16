package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;

public class InventoryScreen extends DefaultScreen {

    public InventoryScreen(final App app) {
        super();
        float buttonWidth = Gdx.graphics.getWidth() / 4;

        mainTable.top().left();

        addButton(mainTable, "START", 0, 0, buttonWidth, 75, false);
        addButton(mainTable, "MENU", 0, 0, buttonWidth, 75, false);
        addButton(mainTable, "OPTIONS", 0, 0, buttonWidth, 75, false);
        addButton(mainTable, "EXIT", 0, 0, buttonWidth, 75, false);

        for (TextButton button : buttons.values()) {
            final ScreenState screenName = ScreenState.valueOf(button.getName());
            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    app.changeScreen(screenName);
                }
            });
        }
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
