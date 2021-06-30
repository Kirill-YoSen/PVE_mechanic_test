package com.company;

public class Main {
    public static void main(String[] args) {
        Unit player = new Unit();
        player.CreateNewPlayer();
        Location location = new Location(player);
        location.Game();
    }
}

