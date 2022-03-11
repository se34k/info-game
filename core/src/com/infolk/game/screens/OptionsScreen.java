package com.infolk.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.ScreenUtils;
import com.infolk.game.App;

/**
 * @author Mihai
 */
public class OptionsScreen implements Screen {

    private Stage stage;

    public OptionsScreen(App app) {
    }

    @Override
    public void show() {
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(3f)));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }

}
