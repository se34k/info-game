package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
	private OrthographicCamera camera;
	private MapController mapController;

	protected SpriteBatch hudBatch;
	private HealthBar bar;

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

		Pixmap color = new Pixmap(500, 500, Pixmap.Format.RGB888);
		color.setColor(Color.WHITE);
		color.fill();

		mainTable.setBackground(new Image(new Texture(color)).getDrawable());

		//Create a camera so we will be able to follow the player later on
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//The camera's position has to be set in the center of the viewport
		camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);

		//This batch is independent from the camera so it does not move with the player
		hudBatch = new SpriteBatch();
	}

	private void testInit() {
		Sprite playerSprite = new Sprite(new Texture(Gdx.files.internal("sprites/janitor_0.png")));
		Playable player = new Playable("Player", 20, new Sprite(playerSprite));

		Sprite obstacleSprite = new Sprite(new Texture(Gdx.files.internal("sprites/badlogic.jpg")));
		NPC obstacle = new NPC("obst", 20, obstacleSprite, 0, 0);
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
	}

	@Override
  	public void render(float delta) {
		if (mapController != null) {
			//We set the camera's position to that of the player in the current MapController on every rendering turn so that it follows
			//the player's movement
			camera.position.set(mapController.getPlayer().getX(), mapController.getPlayer().getY(), 0);
		}

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		super.render(delta);

		//The health bar is rendered as part of hudBatch so it doesn't move
		hudBatch.begin();
		bar.draw(hudBatch);
		hudBatch.end();
	}

	@Override
	public void cleanUp() {
		hudBatch.dispose();
	}

	@Override
	public void update(float delta) {
		if (mapController != null) {
			mapController.onLoop(delta);

			bar.setCurrentHealth(mapController.getPlayer().getHP());
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
