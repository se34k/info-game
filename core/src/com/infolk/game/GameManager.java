package com.infolk.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameManager {
    private ArrayList<Stage> stages;

    public GameManager() {
        stages = new ArrayList<>();
    }

    public Stage createStage() {
        Stage stage = new Stage();
        stages.add(stage);

        return stage;
    }

    public void discardStage() {
        
    }
}
