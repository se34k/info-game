package com.infolk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.audio.Music;
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

	@Override
	public void create() {
		changeScreen(ScreenState.MENU);

		music = Gdx.audio.newMusic(Gdx.files.internal("music/cyberpunk.mp3"));
		music.setLooping(true);
		music.play();
		music.setVolume(App.MUSIC_VOLUME);
	}

	public void changeScreen(ScreenState state) {
		switch (state) {
			case MENU:
				this.setScreen(new MainMenuScreen(this));
				break;

			case START:
				music.stop();
				this.setScreen(new GameScreen(this));
				break;

			case ABOUT:
				this.setScreen(new AboutScreen(this));
				break;

			case OPTIONS:
				this.setScreen(new OptionsScreen(this));
				break;

			case EXIT:
				saveProgress();
				System.exit(0);
				break;

			case INVENTORY:
				this.setScreen(new InventoryScreen(this));
				break;

			default:
				System.out.println("Remind Mihai to implement the " + state.toString() + " screen...");
		}
	}

	public void render() {
		super.render();
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
