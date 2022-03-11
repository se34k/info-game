package com.infolk.game.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.infolk.game.App;

/**
 * 
 * @author Mihai
 *
 */
public class MainMenuScreen implements Screen {

	private Stage stage;
	private Table table;
	private Skin skin;
	private BitmapFont smallFont;
	private BitmapFont mediumFont;

	private Texture logo;

	private HashMap<String, TextButton> buttons;

	private int spaceBetweenButtons = 120;

	private Music music;
	private Sound selectSound;
	private Sound clickSound;

	public MainMenuScreen(final App app) {
		setupFont();
		setupSkinAndStyles();

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		setupLogoAndDescription();

		buttons = new HashMap<String, TextButton>();

		music = Gdx.audio.newMusic(Gdx.files.internal("music/cyberpunk.mp3"));
		music.setLooping(true);
		music.play();
		music.setVolume(App.MUSIC_VOLUME);

		selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.mp3"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));

		addButton("Start");
		addButton("Options");
		addButton("About");
		addButton("Exit");

		for (TextButton button : buttons.values()) {
			final String screenName = button.getName();
			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					clickSound.play(App.EFFECTS_VOLUME);
					if (screenName.equals("Start"))
						music.stop();
					app.changeScreen(screenName);
				}

				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					if (!isPressed()) {
						selectSound.play(App.EFFECTS_VOLUME);
					}
				}

				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				}
			});
		}

	}

	@Override
	public void show() {
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(3f)));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		stage.addAction(Actions.sequence(Actions.alpha(1), Actions.fadeOut(3f)));
	}

	@Override
	public void dispose() {
		smallFont.dispose();
		mediumFont.dispose();
		stage.dispose();
		logo.dispose();
		music.dispose();
		selectSound.dispose();
		clickSound.dispose();
	}

	public void setupFont() {
		smallFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		mediumFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		mediumFont.getData().setScale(1.5f);
	}

	public void setupSkinAndStyles() {
		skin = new Skin();
		skin.add("default", mediumFont);
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		skin.add("red", new Texture(pixmap));
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.over = skin.newDrawable("red", Color.RED);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}

	public void setupLogoAndDescription() {
		logo = new Texture(Gdx.files.internal("gui/logo.png"));
		int width = (int) (Gdx.graphics.getWidth() * 0.9);
		int height = (int) (width * 0.075);
		Image logoImage = new Image(new TextureRegion(logo));
		table.add(logoImage).width((float) width).height(height).spaceBottom(10);
		table.row();

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = smallFont;
		labelStyle.fontColor = Color.WHITE;
		Label descriptionLabel = new Label("An info lk 2022 studios production", labelStyle);
		table.add(descriptionLabel).spaceBottom(100);
		table.row();
	}

	public TextButton addButton(String text) {
		TextButton button = new TextButton(text, skin);
		button.setName(text);
		table.add(button).spaceBottom(spaceBetweenButtons);
		table.row();
		buttons.put(text, button);
		return button;
	}
}
