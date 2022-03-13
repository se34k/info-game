package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;

/**
 * 
 * @author Mihai
 *
 */
public class MainMenuScreen extends DefaultScreen {

	private Music music;

	public MainMenuScreen(final App app) {
		super();

		int width = (int) (Gdx.graphics.getWidth() * 0.9);
		int height = (int) (width * 0.075);
		addImage("gui/logo.png", width, height, 0, 10);
		addText("An info lk 2022 studios production", 0, 100);

		int distance = 100;
		addButton("Start", 0, distance);
		addButton("Options", 0, distance);
		addButton("About", 0, distance);
		addButton("Exit", 0, distance * 2);

		for (TextButton button : buttons.values()) {
			final String screenName = button.getName();
			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					app.changeScreen(screenName);
				}

				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

				}

				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				}
			});
		}

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		music.dispose();
		selectSound.dispose();
		clickSound.dispose();
	}

	@Override
	public void draw() {

	}

}
