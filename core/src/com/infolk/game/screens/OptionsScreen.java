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
        addImage(mainTable, "gui/options.png", width, height, 0, 10, true);
        addText(mainTable, "Remind Mihai to implement this...", 0, 25, true);

        addText(mainTable, "Music Volume:", 25, 20, true);

        final Slider musicSlider = addSlider(mainTable, 0, 1, 0.01f, 0, 25, true);
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

        addText(mainTable, "Effects Volume:", 50, 20, true);

        final Slider effectsSlider = addSlider(mainTable, 0, 1, 0.01f, 0, 25, true);
        effectsSlider.setVisualPercent(App.EFFECTS_VOLUME);
        effectsSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.EFFECTS_VOLUME = effectsSlider.getValue();
            }
        });

        addText(mainTable, "Camera Scale:", 50, 20, true);

        final Slider scaleSlider = addSlider(mainTable, 0, 1, 0.01f, 0, 25, true);
        scaleSlider.setVisualPercent((App.CAMERA_SCALE / 100f) * 25f);
        scaleSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float baseValue = scaleSlider.getValue();
                baseValue *= 100;
                App.CAMERA_SCALE = (int) (baseValue / 25);
                if (App.CAMERA_SCALE < 1)
                    App.CAMERA_SCALE = 1;
                System.out.println(App.CAMERA_SCALE);
            }
        });

        addButton(mainTable, "Back", 0, 0, 300, 75, true).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.goBack();
            }
        });
    }

    @Override
    public void draw() {

    }

    @Override
    public void cleanUp() {
    }

    @Override
    public void update(float delta) {
    }

}