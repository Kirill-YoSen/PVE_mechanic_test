package com.company;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Location {
    private static final String[] items = {"apple", "corn", "beef", "stew", "leather"};
    //private static final String[] weapons = {"common_sword", "axe", "spear", "bow", "cross_bow", "long_axe", "long_sword"};
    //private static final String[] armors = {"leather_armor", "chain_armor", "iron_armor"};


    Event event = new Event();
    Craft craft = new Craft(event.player);
    Menu menu = new Menu();

    int type = 0;
    // Craft zone = 3
    // Empty field = 2
    // Fight zone = 1
    //

    Location(Unit player) {
        this.event.player = player;
        //here you can customize the player
        event.player.weapon.SetWeapon("long_axe");
        event.player.armor.SetArmor("chain_armor");
    }

    public void Game() {
        while (true) {
            if (menu.GameStart()) {
                GenerateLocation();
                if (event.player.PlayerDies()) {
                    return;
                }
            } else {
                System.out.println("See you next time!");
                return;
            }
        }
    }

    public void GenerateLocation() {
        Random random = new Random();
        type = random.nextInt(3) + 1;
        switch (type) {
            case 3:
                System.out.print("[CRAFTING] You are on crafting location!");
                craft.Crafting();
                break;
            case 2:
                System.out.print("[WORLD] You are on empty location! Do you want to try find something useful? {Y / N}: ");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String str = scanner.nextLine();
                    switch (str) {
                        case "Y":
                            int itemID = random.nextInt(items.length+1);
                            if (itemID == items.length) {
                                System.out.println("[WORLD] Location was empty!");
                                return;
                            } else {
                                System.out.println("[WORLD] You have found "+items[itemID]+" !");
                                Item item = new Item();
                                item.GiveItem(items[itemID]);
                                event.player.inventory.AddItem(item);
                            }
                        case "N" :
                            return;
                        default:
                            System.out.println("[INFO] INVALID INFO!");
                    }
                }
            case 1:
                System.out.print("[CRAFTING] You are in fight!");
                int amountofenemies = random.nextInt(3) + 1;
                for (int i = 0; i < amountofenemies; i++) {
                    Unit enemy = new Unit();
                    enemy.CreteNewEnemy(event.player);
                    event.units.add(enemy);
                }
                event.Fight();
        }
    }


}
