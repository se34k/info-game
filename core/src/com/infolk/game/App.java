package com.infolk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

	public Music music;

	@Override
	public void create() {
		changeScreen("Menu");

		music = Gdx.audio.newMusic(Gdx.files.internal("music/cyberpunk.mp3"));
		music.setLooping(true);
		music.play();
		music.setVolume(App.MUSIC_VOLUME);
	}

	public void changeScreen(String screenName) {
		switch (screenName) {
			case "Menu":
				this.setScreen(new MainMenuScreen(this));
				break;

			case "Start":
				music.stop();
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

	public void setVolume(float volume) {
		MUSIC_VOLUME = volume;
		music.setVolume(volume);
	}
}
