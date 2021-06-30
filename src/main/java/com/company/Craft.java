package com.company;

import java.util.Scanner;

public class Craft {
    Unit player;

    Craft(Unit unit) {
        this.player = unit;
    }

    public void Crafting() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[CRAFTING] What do you want to craft? {'E' - leave location; 'A' - leather armor, 4x leather; 'S' - stew, 1x beef and 2x corn}: ");
        while (true) {
            String str = scanner.nextLine();
            switch (str) {
                case "E":
                    return;
                case "A":
                    CraftArmor();
                    break;
                case "S":
                    CraftStew();
                    return;
                default:
                    System.out.println("[INFO] INVALID INPUT!");
            }
        }
    }

    public void CraftArmor() {
        //craft armor
        int l = player.inventory.GetID("leather");
        if (l != -1) {
            if (player.inventory.inventory.get(l).amount > 3) {
                this.player.armor.SetArmor("leather_armor");
                System.out.println("[CRAFTING] You have crafted leather armor!");
                this.player.inventory.inventory.get(l).amount -= 3;
                if (player.inventory.inventory.get(l).amount == 0) {
                    this.player.inventory.inventory.remove(l);
                }
            }
        }
    }

    public void CraftStew() {
        int c = player.inventory.GetID("corn");
        int b = player.inventory.GetID("beef");
        if (c != -1 && b != -1) {
            if (player.inventory.inventory.get(c).amount > 1 && player.inventory.inventory.get(b).amount > 0) {
                Item item = new Item();
                item.GiveItem("stew");
                this.player.inventory.AddItem(item);
                return;
            }
        }
        System.out.println("[CRAFTING] You don't have components to craft stew!");
    }
}
