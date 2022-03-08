package com.infolk.game;

import com.badlogic.gdx.Game;
import com.infolk.game.screens.GameScreen;
import com.infolk.game.screens.MainMenu;

/**
 * @author Mihai
 */
public class App extends Game {

	public static final int SCREEN_WIDTH = 1920;
	public static final int SCREEN_HEIGHT = 1080;

	public GameManager manager;

	public MainMenu menu;

	@Override
	public void create() {
		manager = new GameManager();
		menu = new MainMenu(this);
		this.setScreen(menu);
	}

	public void changeScreen(String screenName) {
		switch (screenName) {
			case "Start":
				this.setScreen(new GameScreen(this));
				break;
			case "Exit":
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

}
