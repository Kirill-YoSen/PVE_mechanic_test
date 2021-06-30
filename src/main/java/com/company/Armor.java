package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Armor {
    String name = "common clothes";
    long defence = 0;

    public void SetArmor(String name) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("armor.json");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject armorObject = (JSONObject) jsonObject.get(name);
            this.name = (String) armorObject.get("name");
            this.defence = (long) armorObject.get("defence");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PrintINFO() {
        System.out.printf("[INFO] You are using %s {defence: %d}\n", this.name, this.defence);
    }
}
