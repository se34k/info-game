package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;
import com.infolk.game.combat.Entity;
import com.infolk.game.combat.Movement;

/**
 * @author Mihai
 */
public class GameScreen extends DefaultScreen {

	Entity player;
	Movement move;

	public GameScreen(final App app) {
		super();
		Sprite playerSprite = new Sprite(new Texture(Gdx.files.internal("sprites/janitor_0.png")));
		player = new Entity("Player", 20, new Sprite(playerSprite));
		move = new Movement();

		addButton(mainTable, "||", 0, 0, false).addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				app.changeScreen(ScreenState.INVENTORY);
			}
		});

		buttons.get("||").setTransform(true);
		buttons.get("||").setScale(0.2f, 1);

		mainTable.top().left();
	}

	@Override
	public void draw() {
		player.sprite.draw(batch);
	}

	@Override
	public void cleanUp() {
		batch.dispose();
	}

	@Override
	public void update(float delta) {
		move.processKeys(player, delta);
	}
}
