package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;
import com.infolk.game.App.ScreenState;
import com.infolk.game.combat.NPC;
import com.infolk.game.combat.Playable;
import com.infolk.game.core.MapController;
import com.infolk.game.screens.components.HealthBar;

/**
 * @author Mihai
 */
public class GameScreen extends DefaultScreen {

	private Playable player;
	private NPC obstacle;

	private HealthBar bar;

	private MapController mapController;

	public GameScreen(final App app) {
		super();

		addButton(mainTable, "||", 0, 0, 75, 75, false).addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				app.changeScreen(ScreenState.INVENTORY);
			}
		});

		mainTable.top().left();

		testInit();

		float width = 500;
		float height = 50;
		float x = 110;
		float y = Gdx.graphics.getHeight() - height * 1.3f;

		bar = new HealthBar(x, y, width, height, 5, 10);
	}

	private void testInit() {
		Sprite playerSprite = new Sprite(new Texture(Gdx.files.internal("sprites/janitor_0.png")));
		player = new Playable("Player", 20, new Sprite(playerSprite));

		Sprite obstacleSprite = new Sprite(new Texture(Gdx.files.internal("sprites/badlogic.jpg")));
		obstacle = new NPC("obst", 20, obstacleSprite);
		obstacle.setPosition(1000, 500);

		loadMap("");

		mapController.addPlayer(player);
		mapController.addEntity(obstacle);
	}

	@Override
	public void draw() {
		if (mapController != null) {
			mapController.draw(batch);
		}
		bar.draw(batch);
	}

	@Override
	public void cleanUp() {
		batch.dispose();
	}

	@Override
	public void update(float delta) {
		if (mapController != null) {
			mapController.onLoop(delta);
		}

		bar.update();
	}

	public void loadMap(String mapId) {
		MapController mc = new MapController(mapId);

		setMapController(mc);
	}

	private void setMapController(MapController mc) {
		mapController = mc;
	}
}
