package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.infolk.game.App;
import com.infolk.game.App.ScreenState;
import com.infolk.game.combat.Playable;
import com.infolk.game.core.GameManager;
import com.infolk.game.core.MapController;
import com.infolk.game.core.interfaces.MapChangeListener;
import com.infolk.game.screens.components.HealthBar;

/**
 * @author Mihai
 */
public class GameScreen extends DefaultScreen implements MapChangeListener {
	private OrthographicCamera camera;
	private MapController mapController;

	protected SpriteBatch hudBatch;
	private HealthBar bar;
	private float barWidth = 500, barHeight = barWidth / 10;

	private GameManager gameManager;

	public GameScreen(final App app) {
		super();

		this.gameManager = app.getGameManager();

		addButton(mainTable, "||", 0, 0, 75, 75, false).addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				app.changeScreen(ScreenState.INVENTORY);
			}
		});

		// addText(mainTable, "Press E to enter", 100, 0, false);

		float barX = 90, barY = Gdx.graphics.getHeight() - barHeight * 1.3f;
		bar = new HealthBar(barX, barY, barWidth, barHeight, 5, 10);

		mainTable.top().left();

		Pixmap color = new Pixmap(500, 500, Pixmap.Format.RGB888);
		color.setColor(Color.WHITE);
		color.fill();

		// Create a camera so we will be able to follow the player later on
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / (App.CAMERA_SCALE * 5),
				Gdx.graphics.getHeight() / (App.CAMERA_SCALE * 5));
		// The camera's position has to be set in the center of the viewport
		camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);

		// This batch is independent from the camera so it does not move with the player
		hudBatch = new SpriteBatch();

		setMapController(app.getGameManager().getCurrentMap());

		if (mapController.getPlayer() == null) {
			testInit();
		}

		app.getGameManager().registerMapChangeListener(this);
	}

	private void testInit() {
		Sprite playerSprite = new Sprite(new Texture(Gdx.files.internal("sprites/janitor_0.png")));
		Playable player = new Playable("Player", 10, new Sprite(playerSprite), 0, 0);

		mapController.spawnPlayerAt(player, 1);
	}

	@Override
	public void draw() {
		if (mapController != null) {
			mapController.draw(batch);
		}
	}

	@Override
	public void render(float delta) {
		if (mapController != null) {
			// We set the camera's position to that of the player in the current
			// MapController on every rendering turn so that it follows
			// the player's movement
			camera.position.set(mapController.getPlayer().getX(), mapController.getPlayer().getY(), 0);
		}

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (mapController != null) {
			mapController.renderer.setView(camera);
		}

		super.render(delta);

		// The health bar is rendered as part of hudBatch so it doesn't move
		hudBatch.begin();
		bar.draw(hudBatch);
		hudBatch.end();

		stage.draw();
	}

	@Override
	public void cleanUp() {
		hudBatch.dispose();
		gameManager.unregisterMapChangeListener(this);
	}

	@Override
	public void update(float delta) {
		if (mapController != null) {
			mapController.onLoop(delta);

			bar.setCurrentHealth(mapController.getPlayer().getHP());
		}

		bar.update();
	}

	public void setMapController(MapController mc) {
		mapController = mc;
	}

	@Override
	public void onMapChange(MapController newMap) {
		// This listener is used to ensure the displayed map is changed as soon as it is
		// changed in the GameManager
		setMapController(newMap);
	}
}
