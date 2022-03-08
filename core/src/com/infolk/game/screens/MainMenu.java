package com.infolk.game.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.infolk.game.App;

/**
 * 
 * @author Mihai
 *
 */
public class MainMenu implements Screen {

	private Stage stage;
	private Table table;
	private Skin skin;
	private BitmapFont smallFont;
	private BitmapFont buttonFont;

	private Texture logo;

	private HashMap<String, TextButton> buttons;

	private int spaceBetweenButtons = 120;

	public MainMenu(final App app) {
		setupFont();
		setupSkinAndStyles();

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		setupLogoAndDescription();

		buttons = new HashMap<String, TextButton>();

		Music m = Gdx.audio.newMusic(Gdx.files.internal("music/cyberpunk.mp3"));
		m.setLooping(true);
		m.play();

		addButton("Start").addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				app.startGame();
			}
		});

		addButton("Options").addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

			}
		});

		addButton("About").addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

			}
		});

		addButton("Exit").addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				System.exit(0);
			}
		});
	}

	@Override
	public void show() {

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

	}

	@Override
	public void dispose() {
		smallFont.dispose();
		buttonFont.dispose();
		stage.dispose();
		logo.dispose();
	}

	public void setupFont() {
		smallFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		buttonFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		buttonFont.getData().setScale(1.5f);
	}

	public void setupSkinAndStyles() {
		skin = new Skin();
		skin.add("default", buttonFont);
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.over = skin.newDrawable("white", Color.RED);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

	}

	public void setupLogoAndDescription() {
		logo = new Texture(Gdx.files.internal("gui/logo.png"));
		int width = (int) (App.SCREEN_WIDTH * 0.9);
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
		table.add(button).spaceBottom(spaceBetweenButtons);
		table.row();
		buttons.put(text, button);
		return button;
	}
}
