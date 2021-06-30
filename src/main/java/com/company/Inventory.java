package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private static final int inventory_size = 3;
    ArrayList<Item> inventory = new ArrayList<>();

    public void PrintINFO() {
        int id = 0;
        for (Item item : inventory) {
            item.PrintINFO(id);
            id++;
        }
    }

    public void DropItem() {
        int id = GetItemID();
        this.inventory.remove(id);
    }

    public void DropItemID(int id) {
        //int id = GetItemID();
        this.inventory.remove(id);
    }

    public void AddItem(Item item) {
        if (inventory.contains(item)) {
            int id = inventory.indexOf(item);
            this.inventory.get(id).amount++;
        } else {
            if (inventory.size() == inventory_size) {
                Replace(item);
            } else {
                this.inventory.add(item);
            }
        }
    }

    public void Replace(Item item) {
        PrintINFO();
        System.out.println("You have to replace an item! (Y - continue / N - stop)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("Y")) {
                int id = GetItemID();
                this.inventory.set(id, item);
                return;
            } else if (str.equals("N")) {
                return;
            } else {
                System.out.println("[INFO] INVALID INPUT");
            }
        }
    }

    public int GetItemID() {
        Scanner scanner = new Scanner(System.in);
        int id = -1;
        while (true) {
            System.out.printf("Enter item ID (0 - %d): ", inventory.size()-1);
                try {
                    String str = scanner.nextLine();
                    id = Integer.parseInt(str);
                } catch (Exception e) {
                    System.out.println("[INFO] Invalid input!");
                }
                if (id >= 0 && id < inventory.size()) {
                    return id;
                } else {
                    System.out.println("[INFO] Invalid input!");
                }
        }
    }

    public int GetID(String name) {
         for (Item item : inventory) {
             if (name.compareTo(item.name) == 0) {
                 return inventory.indexOf(item);
             }
         }
         return -1;
    }
}
