package com.company;

import java.util.Random;
import java.util.Scanner;

public class Unit extends Event implements UnitsActions {
    private static final String[] races = {"dwarf", "skeleton", "orc", "spider"};
    private static final String[] weapons = {"common_sword", "axe", "spear", "bow", "cross_bow", "long_axe", "long_sword"};
    private static final String[] armors = {"leather_armor", "chain_armor", "iron_armor"};
    private static final int maxLvL = 10;

    String name = "";
    String race = "human";
    Weapons weapon = new Weapons();
    Armor armor = new Armor();
    Inventory inventory = new Inventory();
    int health = 10;
    int lvl = 1;
    int exp = 0; //just for player
    int speed = 1;

    public void CreateNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your nickname: ");
        this.name = scanner.nextLine();
    }

    public void CreteNewEnemy(Unit player) {
        Random random = new Random();
        //random stuff formula is that -  Random{stuff.length - maxLvL + this.LvL - 1}
        this.lvl = random.nextInt(player.lvl);
        this.health += lvl;
        this.race = races[random.nextInt(races.length - 1)];

        //weapon
        if (0 < (weapons.length - maxLvL + lvl)) {
            this.weapon.SetWeapon(weapons[random.nextInt(weapons.length - maxLvL + lvl) ]);
        }
        //armor
        if (0 < (armors.length - maxLvL + lvl)) {
            this.armor.SetArmor(armors[random.nextInt(armors.length - maxLvL + lvl) ]);
        }
    }

    public void PrintINFO() {
        System.out.println();
        System.out.printf("[INFO] You are %s, your name is %s {health: %d, your level: %d, experience: %d}\n", this.race, this.name, this.health, this.lvl, this.exp);
        inventory.PrintINFO();
        weapon.PrintINFO();
        armor.PrintINFO();
    }

    public void PrintEnemyINFO(Unit target) {
        System.out.printf("[INFO] ID %d) Your enemy is %s, it has %d health points, it's using %s, and it's using %s, range between you and him is %d\n", units.indexOf(target), this.race, this.health, this.weapon.name, this.armor.name, ranges.get(units.indexOf(target)));
    }

    public void EnemyDies(Unit unit) {
        if (unit.health < 1) {
            System.out.printf("[BATTLE] Player has killed the %s, congrats! +1 exp.\n", unit.race);
            this.exp++;
            units.remove(unit);
            ranges.remove(unit);
        }
    }

    public boolean PlayerDies() {
        if (health <= 0) {
            System.out.printf("\n###..You are dead..###\n");
            return true;
            //have to delete a save file
        } else {
            return false;
        }
    }

    public void LvLUp() {
        if (exp % (10 * lvl) == 0 && exp > 0) {
            this.health+=lvl;
            this.lvl++;
            this.exp = 10;
            System.out.printf("[INFO] You have reached %d level!\n", this.lvl);
        }
    }

    public void MoveTo(Unit target, int id) {
        ranges.set(id, ranges.get(id) - speed);
        System.out.printf("[BATTLE] %s is moving closer to %s, now range is %d\n", this.race, target.race, ranges.get(id));
    }

    public void MoveFrom(Unit target, int id) {
        ranges.set(id, ranges.get(id) + speed);
        System.out.printf("[BATTLE] %s is trying to escape from %s, now range is %d\n", this.race, target.race, ranges.get(id));
    }

    public void StayOn() {
        //stay on ¯\_(ツ)_/¯ what have you expected? Lol
        System.out.printf("[BATTLE] %s will stay on his position\n", race);
    }

    public void Attack(Unit target, int id) {
        Random random = new Random();
        int failure = random.nextInt(5);


        if (ranges.get(id) <= this.weapon.attack_range) {
            if (failure != 1) {
                target.health -= weapon.damage + target.armor.defence;
                System.out.printf("[BATTLE] %s has successfully attacked %s, and now it has %d hp.!\n", race, target.race, target.health);
            } else {
                System.out.printf("[BATTLE] %s missed!\n", race);
            }
        } else {
            System.out.println("[BATTLE] Range between you is too big for attack...\n");
        }
    }

    public void UseItem() {
        if (inventory.inventory.isEmpty()) {
            System.out.println("[INFO] You don't have items in inventory");
        } else {
            int id = inventory.GetItemID();
            if (inventory.inventory.get(id).regeneration > 0) {
                this.health += inventory.inventory.get(id).regeneration;
                this.inventory.DropItemID(id);
            } else {
                System.out.println("[INFO] You can't use that item, because you can't");
            }
        }
    }

    public void DropItem() {
        if (inventory.inventory.isEmpty()) {
            System.out.println("[INFO] You don't have items in inventory");
        } else {
            inventory.DropItem();
        }
    }

    public void ReplaceItem(Item item) {
        if (inventory.inventory.isEmpty()) {
            System.out.println("[INFO] You don't have items in inventory");
        } else {
            inventory.Replace(item);
        }
    }



}
