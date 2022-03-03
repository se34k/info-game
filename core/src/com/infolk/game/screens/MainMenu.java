package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.infolk.game.App;

public class MainMenu implements Screen {
	
	private App app;
	
	private OrthographicCamera camera;
	
	private Texture logo;
	private Rectangle logoRect;
	
	public MainMenu(App app) {
		this.app = app;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
		
		logo = new Texture(Gdx.files.internal("gui/logo.png"));
		int width = 1800;
		int height = 100;
		logoRect = new Rectangle((App.SCREEN_WIDTH - width) / 2, App.SCREEN_HEIGHT - 250, width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		
		camera.update();
		app.batch.setProjectionMatrix(camera.combined);
		
		app.batch.begin();
		
		app.batch.draw(logo, logoRect.x, logoRect.y, logoRect.width, logoRect.height);
		
		GlyphLayout description = new GlyphLayout();
		description.setText(app.font, "An info lk studios production");
		double descriptionX = App.SCREEN_WIDTH / 2 - description.width / 2;
		double descriptionY = logoRect.y - logoRect.height * 0.75;
		app.font.draw(app.batch, description, (int)descriptionX, (int)descriptionY);
		
		app.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		logo.dispose();
	}

}
