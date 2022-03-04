package com.infolk.game;

import com.badlogic.gdx.Game;
import com.infolk.game.screens.GameScreen;
import com.infolk.game.screens.MainMenu;

public class App extends Game {
	
	public static final int SCREEN_WIDTH = 1920;
	public static final int SCREEN_HEIGHT = 1080;
	
	public GameManager manager;
	
	public App() {
		super();
		
		manager = new GameManager();
	}
	
	public void startGame() {
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void create() {
		this.setScreen(new MainMenu(this));
	}
	
	public void render() {
		super.render();
	}
	
	public void dispose() {
	}
	
}
