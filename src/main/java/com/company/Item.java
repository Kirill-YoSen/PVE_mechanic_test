package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Item {
    String name;
    String description;
    boolean craftable;
    long regeneration;
    int amount;

    public void GiveItem(String name) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("items.json");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject weaponobject = (JSONObject) jsonObject.get(name);
            this.name = (String) weaponobject.get("name");
            this.description = (String) weaponobject.get("description");
            this.craftable = (Boolean) weaponobject.get("craftable");
            this.regeneration = (long) weaponobject.get("regeneration");
            this.amount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PrintINFO(int id) {
        System.out.printf("[INFO] %d) ID it's %s x%d, %s\n",id, this.name, this.amount, this.description);
    }
}
