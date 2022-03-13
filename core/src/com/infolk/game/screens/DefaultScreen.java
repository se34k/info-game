package com.infolk.game.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    protected SpriteBatch batch;

    protected Stage stage;
    protected Table mainTable;
    protected Skin skin;

    protected Sound selectSound;
    protected Sound clickSound;

    protected HashMap<String, TextButton> buttons;

    public DefaultScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("craftacular/skin/craftacular-ui.json"));

        buttons = new HashMap<>();

        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));

        mainTable = new Table(skin);
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        batch = new SpriteBatch();
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
        batch.begin();
        draw();
        batch.end();
        update(delta);
    }

    public abstract void update(float delta);

    public abstract void draw();

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
        clickSound.dispose();
        selectSound.dispose();
        cleanUp();
    }

    public abstract void cleanUp();

    protected void addImage(Table table, String relativePath, float width, float height, float marginTop,
            float marginBottom, boolean row) {
        Texture texture = new Texture(Gdx.files.internal(relativePath));
        Image image = new Image(new TextureRegion(texture));
        table.add(image).width(width).height(height).space(marginTop, 0, marginBottom, 0);
        if (row)
            table.row();
    }

    protected void addText(Table table, String text, float marginTop, float marginBottom, boolean row) {
        Label label = new Label(text, skin);
        table.add(label).space(marginTop, 0, marginBottom, 0);
        if (row)
            table.row();
    }

    protected TextButton addButton(Table table, String text, float marginTop, float marginBottom, boolean row) {
        TextButton button = new TextButton(text, skin);
        button.setName(text);
        table.add(button).space(marginTop, 0, marginBottom, 0);
        if (row)
            table.row();
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(App.EFFECTS_VOLUME);
            }

        });
        buttons.put(text, button);
        return button;
    }

    protected Slider addSlider(Table table, float min, float max, float stepSize, float marginTop, float marginBottom,
            boolean row) {
        Slider slider = new Slider(min, max, stepSize, false, skin);
        table.add(slider).space(marginTop, 0, marginBottom, 0);
        if (row)
            table.row();
        return slider;
    }
}
