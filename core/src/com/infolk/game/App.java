package com.infolk.game;

import com.badlogic.gdx.Game;
import com.infolk.game.screens.AboutScreen;
import com.infolk.game.screens.GameScreen;
import com.infolk.game.screens.MainMenuScreen;
import com.infolk.game.screens.OptionsScreen;

/**
 * @author Mihai
 */
public class App extends Game {

	public GameManager manager;

	public static float MUSIC_VOLUME = 0.05f;
	public static float EFFECTS_VOLUME = 0.5f;

	public MainMenuScreen menu;

	@Override
	public void create() {
		manager = new GameManager();
		menu = new MainMenuScreen(this);
		this.setScreen(menu);
	}

	public void changeScreen(String screenName) {
		switch (screenName) {
			case "Start":
				this.setScreen(new GameScreen(this));
				break;

			case "About":
				this.setScreen(new AboutScreen(this));
				break;

			case "Options":
				this.setScreen(new OptionsScreen(this));
				break;

			case "Exit":
				saveProgress();
				menu.dispose();
				System.exit(0);
				break;

			default:
				System.out.println("Remind Mihai to implement the " + screenName + " screen...");
		}
	}

	public void render() {
		super.render();
	}

	public void dispose() {
	}

	public void saveProgress() {

	}

}
