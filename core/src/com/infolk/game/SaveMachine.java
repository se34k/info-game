/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package com.infolk.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.ValueType;

/**
 *
 * @author max
 */
public class SaveMachine {

    private static JsonValue cache;
    private static FileHandle saveFile;
    
    public SaveMachine() {
        Preferences prefs = Gdx.app.getPreferences("gdxgame");
        String filePath = prefs.getString("saveFile", "persistent.json");
        saveFile = Gdx.files.internal(filePath);
    }
    
    public static void loadFromFile() {
        cache = new JsonReader().parse(saveFile.readString());
    }
    
    public static void saveToFile() {
        String saveData = cache.asString();
        saveFile.writeString(saveData, false);
    }
    
    public static void saveValue(Object value, String path) {
        JsonValue child = cache;
        for(String s : path.split("/")) {
            if(!child.has(s)) {
                child.addChild(s, new JsonValue((ValueType) new Object()));
            }
            child = child.get(s);
        }
        // child = value;
    }
    
    public static JsonValue loadValue(String path) {
        JsonValue child = cache;
        for(String s : path.split("/")) {
            if(!child.has(s)) {
                child.addChild(s, new JsonValue((ValueType) new Object()));
            }
            child = child.get(s);
        }
        return child;
    }

}
