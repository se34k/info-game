package com.infolk.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.infolk.game.App;

/**
 * @author Mihai
 */
public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Janitor Tales";
		config.width = 1280;
		config.height = 720;
		config.x = -1;
		config.y = -1;
		config.fullscreen = false;
		config.addIcon("icons/icon.png", FileType.Internal);
		new LwjglApplication(new App(), config);
	}
}
