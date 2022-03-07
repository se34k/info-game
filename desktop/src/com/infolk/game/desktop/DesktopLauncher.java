package com.infolk.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.infolk.game.App;

/**
 * @author Mihai
 */
public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Scheiss Zelda like game v.069";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new App(), config);
	}
}
