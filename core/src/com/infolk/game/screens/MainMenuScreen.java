package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;

/**
 * 
 * @author Mihai
 *
 */
public class MainMenuScreen extends DefaultScreen {

	public MainMenuScreen(final App app) {
		super();

		int width = (int) (Gdx.graphics.getWidth() * 0.9);
		int height = (int) (width * 0.075);
		addImage(mainTable, "gui/logo.png", width, height, 0, 10, true);
		addText(mainTable, "An info lk 2022 studios production", 0, 75, true);

		int distance = 100;
		addButton(mainTable, "START", 0, distance, 300, 75, true);
		addButton(mainTable, "OPTIONS", 0, distance, 300, 75, true);
		addButton(mainTable, "ABOUT", 0, distance, 300, 75, true);
		addButton(mainTable, "EXIT", 0, distance * 2, 300, 75, true);

		for (TextButton button : buttons.values()) {
			final ScreenState screenName = ScreenState.valueOf(button.getName());
			button.addListener(new ClickListener() {
				@Override
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
