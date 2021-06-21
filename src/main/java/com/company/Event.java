package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Event{
    public static ArrayList<Integer> ranges = new ArrayList<>();
    public Unit player = null;
    public static ArrayList<Unit> units = new ArrayList<>();

    //public void AddEnemy(Unit unit) {this.units.add(unit);}

    public void Fight() {
        //create a location for fight
        //range between enemies
        Random random = new Random();
        for (int i = 0; i < units.size(); i++) {
            ranges.add(random.nextInt(25));
        }
        boolean running = true;

        while (running) {
            PlayerActionsBattle();
            if (units.size() <= 0) {
                running = false;
            }
            System.out.println();
            for (int i = 0; i < units.size(); i++) {
                EnemyAction(i);
                if (player.PlayerDies()) {
                    return;
                }
            }
        }
        System.out.println("##>> CONGRATULATIONS! <<##");
    }

    private void EnemyAction(int id) {
        if (ranges.get(id) <= units.get(id).weapon.attack_range) {
            units.get(id).Attack(player);
            return;
        }

        if (ranges.get(id) > units.get(id).weapon.attack_range && units.get(id).health >= 5) {
            units.get(id).MoveTo(player, id);
        } else {
            Random random = new Random();
            int rand = random.nextInt(3);
            if (rand != 1) {
                units.get(id).MoveFrom(player, id);
            } else {
                units.get(id).StayOn();
            }
        }
    }

    private void PlayerActionsBattle() {
        int id;
        while (true) {
            System.out.print("\n[BATTLE] You have to chose an action (type a number) \n{1 - INFO (about all enemies, also you after putting that command, you will be able to make a move), 2 - ATTACK (ID), 3 - MOVE TO (ID), 4 - MOVE FROM (ID, 5 - STAY ON POSITION (skip your move)}: ");
            int action = PlayerInputAction();
            switch (action) {
                case 1:
                    player.PrintINFO();
                    for (Unit unit : units) {
                        unit.PrintEnemyINFO(unit);
                    }
                    continue;
                case 2:
                    id = PlayerInputId();
                    player.Attack(units.get(id));
                    player.EnemyDies(units.get(id));
                    player.LvLUp();
                    return;
                case 3:
                    id = PlayerInputId();
                    player.MoveTo(units.get(id), id);
                    return;
                case 4:
                    id = PlayerInputId();
                    player.MoveFrom(units.get(id), id);
                case 5:
                    player.StayOn();
                    return;
            }
        }
    }

    private int PlayerInputAction() {
        Scanner scanner = new Scanner(System.in);
        int a;
        while (true) {
            try {
                String str = scanner.nextLine();
                a = Integer.parseInt(str);
                if (a > 0 && a < 6) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("[ERROR] INVALID INPUT!");
                continue;
            }
        }
        return a;
    }

    private int PlayerInputId() {
        Scanner scanner = new Scanner(System.in);
        int a;
        while (true) {
            System.out.print("[BATTLE] Enter ID: ");
            try {
                String str = scanner.nextLine();
                a = Integer.parseInt(str);
                if (a >= 0 && a < units.size()) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("[ERROR] INVALID INPUT!");
                continue;
            }
        }
        return a;
    }
}
