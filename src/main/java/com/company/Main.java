package com.company;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if (!menu.GameStart()) {return;}
        Unit player = new Unit();
        player.CreateNewPlayer();
        Event event = new Event();
        event.player = player;
        for (int w = 0; w < 7; w++ ) {
            event.units.clear();
            player.weapon.SetWeapon("long_sword");
            for (int i = 0; i < 3; i++) {
                Unit enemy = new Unit();
                enemy.CreteNewEnemy(player);
                event.units.add(enemy);
            }
            event.Fight();
            System.out.printf("You have passed %d/%d", w+1, 7);
            if (!menu.GameStart() && w != 6) {return;}
        }
        System.out.println("Thanks for playing! This alfa 1.0");
    }
}

