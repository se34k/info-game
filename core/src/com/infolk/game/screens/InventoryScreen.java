package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;

public class InventoryScreen extends DefaultScreen {

    public InventoryScreen(final App app) {
        super();
        float buttonWidth = Gdx.graphics.getWidth() / 4;

        Table upperLeftTable = new Table(skin);
        Table upperRightTable = new Table(skin);
        upperLeftTable.top().left();
        upperRightTable.top().left();

        addButton(upperLeftTable, "START", 0, 0, buttonWidth, 75, false);
        addButton(upperLeftTable, "MENU", 0, 0, buttonWidth, 75, false);
        addButton(upperRightTable, "OPTIONS", 0, 0, buttonWidth, 75, false);
        addButton(upperRightTable, "EXIT", 0, 0, buttonWidth, 75, true);

        for (TextButton button : buttons.values()) {
            final ScreenState screenName = ScreenState.valueOf(button.getName());
            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    app.changeScreen(screenName);
                }
            });
        }

        Table leftTable = new Table(skin);
        Table rightTable = new Table(skin);

        addImage(leftTable, "sprites/janitor_0.png", 300, 450, 50, 0, false);
        addImage(rightTable, "sprites/janitor_0.png", 300, 450, 50, 0, false);

        mainTable.add(upperLeftTable);
        mainTable.add(upperRightTable);
        mainTable.row();
        mainTable.add(leftTable).expand();
        mainTable.add(rightTable).expand();

        mainTable.top();
        stage.setDebugAll(true);
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
