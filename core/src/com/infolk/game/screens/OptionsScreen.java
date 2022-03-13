package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;

/**
 * @author Mihai
 */
public class OptionsScreen extends DefaultScreen {

    public OptionsScreen(final App app) {
        super();

        int width = (int) (Gdx.graphics.getWidth() * 0.6);
        int height = (int) (width * 0.2);
        addImage("gui/options.png", width, height, 0, 10);
        addText("Remind Mihai to implement this...", 0, 50);

        addText("Music Volume:", 100, 20);

        final Slider musicSlider = addSlider(0, 1, 0.01f, 0, 25);
        musicSlider.setVisualPercent(App.MUSIC_VOLUME);
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!app.music.isPlaying()) {
                    app.music.play();
                    app.music.setPosition(app.musicPosition);
                }
                app.setVolume(musicSlider.getValue());
                if (musicSlider.getValue() == 0) {
                    app.musicPosition = app.music.getPosition();
                    app.music.stop();
                }
            }
        });

        addText("Effects Volume:", 100, 20);

        final Slider effectsSlider = addSlider(0, 1, 0.01f, 0, 50);
        effectsSlider.setVisualPercent(App.EFFECTS_VOLUME);
        effectsSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.EFFECTS_VOLUME = effectsSlider.getValue();
            }
        });

        addButton("Back", 100, 100).addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                app.changeScreen("Menu");
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            }
        });
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void draw() {

    }

}