package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.ScreenUtils;
import com.infolk.game.App;
import com.infolk.game.combat.Entity;
import com.infolk.game.combat.Movement;
import com.infolk.game.core.GameCore;

/**
 * @author Mihai
 */
public class GameScreen implements Screen {

	private Stage stage;
	private GameCore gc;

	// Only Testing //
		ShapeRenderer renderer;
		Entity testPlayer1 = new Entity("Test1", 20, new Sprite(new Texture(Gdx.files.internal("core\\assets\\sprites\\janitor_0.png"))));
		Entity testPlayer2 = new Entity("Test2", 20, new Sprite(new Texture(Gdx.files.internal("core\\assets\\sprites\\janitor_0.png"))));
		Movement move = new Movement();
		SpriteBatch batch = new SpriteBatch();
	//

	public GameScreen(App app) {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(3f)));
	}

	@Override
	public void render(float delta) {
		update(delta);
	}

	private void update(float delta) {
		ScreenUtils.clear(Color.BLACK);
		stage.act(delta);
		stage.draw();

		// Only Testing
			move.processKeys(testPlayer1, delta);
			batch.begin();
			testPlayer1.sprite.draw(batch);
			batch.end();
		//
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

	}

}
