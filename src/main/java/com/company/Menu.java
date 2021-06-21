package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public boolean GameStart() {
        System.out.printf("Do you want to start? {Y / N}: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            str = str.toUpperCase(Locale.ROOT);
            switch (str) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("INVALID INPUT!");
            }
        }
    }
}
