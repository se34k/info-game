package com.infolk.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infolk.game.App;

public abstract class DefaultScreen implements Screen {

    protected Stage stage;
    protected Table table;
    protected Skin skin;
    protected BitmapFont smallFont;
    protected BitmapFont mediumFont;

    private Sound selectSound;
    private Sound clickSound;

    public DefaultScreen() {
        setupFont();
        setupSkinAndStyles();

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
    public abstract void render(float delta);

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

    private void setupFont() {
        smallFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        mediumFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        mediumFont.getData().setScale(1.5f);
    }

    private void setupSkinAndStyles() {
        skin = new Skin();
        skin.add("default", mediumFont);
        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        skin.add("red", new Texture(pixmap));
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.over = skin.newDrawable("red", Color.RED);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    protected void addImage(String relativePath, int width, int height, int margin) {
        Texture texture = new Texture(Gdx.files.internal(relativePath));
        Image image = new Image(new TextureRegion(texture));
        table.add(image).width(width).height(height).space(margin / 2, 0, margin / 2, 0);
        table.row();
    }

    protected void addText(String text, int margin) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = smallFont;
        labelStyle.fontColor = Color.WHITE;
        Label label = new Label(text, labelStyle);
        table.add(label).space(margin / 2, 0, margin / 2, 0);
        table.row();
    }

    protected TextButton addButton(String text, int margin) {
        TextButton button = new TextButton(text, skin);
        button.setName(text);
        table.add(button).space(margin / 2, 0, margin / 2, 0);
        table.row();
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(App.EFFECTS_VOLUME);
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isPressed()) {
                    selectSound.play(App.EFFECTS_VOLUME);
                }
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            }
        });
        return button;
    }
}
