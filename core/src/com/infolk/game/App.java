package com.infolk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.infolk.game.core.GameManager;
import com.infolk.game.screens.AboutScreen;
import com.infolk.game.screens.GameScreen;
import com.infolk.game.screens.InventoryScreen;
import com.infolk.game.screens.MainMenuScreen;
import com.infolk.game.screens.OptionsScreen;

/**
 * @author Mihai
 */
public class App extends Game {

	public enum ScreenState {
		MENU,
		ABOUT,
		OPTIONS,
		START,
		EXIT,
		INVENTORY
	}

	public GameManager manager;

	public static float MUSIC_VOLUME = 0.05f;
	public static float EFFECTS_VOLUME = 0.5f;

	public Music music;
	public float musicPosition;

	private ScreenState lastScreen;
	private ScreenState currentScreen;

	public static int CAMERA_SCALE = 3;

	@Override
	public void create() {
		changeScreen(ScreenState.MENU);

		music = Gdx.audio.newMusic(Gdx.files.internal("music/cyberpunk.mp3"));
		music.setLooping(true);
		music.play();
		music.setVolume(App.MUSIC_VOLUME);

		Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
		Texture texture = new Texture(Gdx.files.internal("gui/cursor.png"));
		texture.getTextureData().prepare();
		pixmap.drawPixmap(texture.getTextureData().consumePixmap(), 0, 0);
		Cursor cursor = Gdx.graphics.newCursor(pixmap, 32, 20);
		Gdx.graphics.setCursor(cursor);

		manager = new GameManager();

		// manager.createAndAddMap("test");
		// manager.changeCurrentMap("test");

		manager.startGame();
		

		lastScreen = ScreenState.MENU;
		currentScreen = ScreenState.MENU;
	}

	public void changeScreen(ScreenState state) {
		lastScreen = currentScreen;
		currentScreen = state;

		setScreen(getScreen(state));
	}

	public void goBack() {
		changeScreen(lastScreen);
	}

	private Screen getScreen(ScreenState state) {
		switch (state) {
			case MENU:
				return new MainMenuScreen(this);
			case START:
				music.stop();
				return new GameScreen(this);
			case ABOUT:
				return new AboutScreen(this);
			case OPTIONS:
				return new OptionsScreen(this);
			case EXIT:
				saveProgress();
				System.exit(0);
				break;
			case INVENTORY:
				return new InventoryScreen(this);
			default:
				System.out.println("Remind Mihai to implement the " + state.toString() + " screen...");
		}

		return null;
	}

	public GameManager getGameManager() {
		return manager;
	}

	public void dispose() {
	}

	public void saveProgress() {

	}

	public void setVolume(float volume) {
		MUSIC_VOLUME = volume;
		music.setVolume(volume);
	}
}
