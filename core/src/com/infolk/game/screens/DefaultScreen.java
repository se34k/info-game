package com.infolk.game.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.infolk.game.App;

public abstract class DefaultScreen implements Screen {

    protected Stage stage;
    protected Table table;
    protected Skin skin;

    protected Sound selectSound;
    protected Sound clickSound;

    protected HashMap<String, TextButton> buttons;

    public DefaultScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("craftacular/skin/craftacular-ui.json"));

        buttons = new HashMap<>();

        selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.mp3"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
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
        draw();
    }

    public abstract void draw();

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override
    public abstract void pause();

    @Override
    public abstract void resume();

    @Override
    public void hide() {
        stage.addAction(Actions.sequence(Actions.alpha(1), Actions.fadeOut(3f)));
    }

    @Override
    public abstract void dispose();

    protected void addImage(String relativePath, float width, float height, float marginTop, float marginBottom) {
        Texture texture = new Texture(Gdx.files.internal(relativePath));
        Image image = new Image(new TextureRegion(texture));
        table.add(image).width(width).height(height).space(marginTop, 0, marginBottom, 0);
        table.row();
    }

    protected void addText(String text, float marginTop, float marginBottom) {
        Label label = new Label(text, skin);
        table.add(label).space(marginTop, 0, marginBottom, 0);
        table.row();
    }

    protected TextButton addButton(String text, float marginTop, float marginBottom) {
        TextButton button = new TextButton(text, skin);
        button.setName(text);
        table.add(button).space(marginTop, 0, marginBottom, 0);
        table.row();
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(App.EFFECTS_VOLUME);
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // selectSound.play(App.EFFECTS_VOLUME);
            }
        });
        buttons.put(text, button);
        return button;
    }

    protected Slider addSlider(float min, float max, float stepSize, float marginTop, float marginBottom) {
        Slider slider = new Slider(min, max, stepSize, false, skin);
        table.add(slider).space(marginTop, 0, marginBottom, 0);
        table.row();
        return slider;
    }
}
