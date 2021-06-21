package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Weapons {
    String name = "hands";
    long damage = 1;
    long attack_range = 1;
    long attack_speed = 1;

    public void SetWeapon(String name) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("weapon.json");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject weaponobject = (JSONObject) jsonObject.get(name);
            this.name = (String) weaponobject.get("name");
            this.damage = (long) weaponobject.get("weapon_damage");
            this.attack_range = (long) weaponobject.get("weapon_range");
            this.attack_speed = (long) weaponobject.get("speed_attack");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PrintINFO() {
        System.out.printf("[INFO] You are using %s, specks {damage: %d; range: %d; attack speed: %d}\n", this.name, this.damage, this.attack_range, this.attack_speed);
    }

}
