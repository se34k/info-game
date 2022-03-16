package com.infolk.game.save;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveMachine {
  
  private static JSONObject obj = new JSONObject();
  private static boolean init = false;
  private static FileHandle saveFile;
  
  //Init VOR speichern/laden aufrufen, gibt true zurück, wenn vollständig initialisiert
  public static boolean init() {
    if(init) {
      return true;
    }
    if(!Gdx.files.isLocalStorageAvailable()) {
      return false;
    }
    Preferences prefs = Gdx.app.getPreferences("janitor-tales");
    String savePath = prefs.getString("savePath", "saves/save");
    saveFile = Gdx.files.local(savePath);
    if(!saveFile.exists()) {
      saveFile.writeString("", false);
    }
    return true;
  }
  
  //Speicher einen Wert mit einem Namen im JSONObject
  public static void saveValue(String name, Object value) {
    if(!init)
      return;
    obj.put(name, value);
  }
  
  //Lädt einen Wert mit einem Namen aus dem JSONObject
  public static Object loadValue(String name) {
    if(!init)
      return null;
    return obj.get(name);
  }
  
  //Schreibt en Inhalt des JSONObjects in die Savefile
  private static void saveToFile(String path) {
    if(!init)
      return;
    saveFile.writeString(obj.toString(), false);
  }
  
  //Lädt die Daten aus der Savefile ins JSONObject
  private static void loadFromFile(String path) {
    if(!init)
      return;
    obj = (JSONObject) JSONValue.parse(saveFile.readString());
  }
  
}
