package com.george;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* 
         * Starting the Game!
         */
        System.out.println("Sokobani starts!");

        GameMap level = new GameMap();

        level.loadFromFile("level1.txt");
        
        /*
         * Implemention of the Scanner object so the movement can be done through the keyboard
         */
        Scanner scanner = new Scanner(System.in);

        while (true) {
            level.update();
            System.out.print("Enter move (w/a/s/d, q=quit): ");
            String input = scanner.nextLine();
            if (input.equals("q"))
                break;

            char c = Character.toLowerCase(input.charAt(0));

            switch (c) {
                case 'w' -> InputHandler.fireMoveUp(level);
                case 'a' -> InputHandler.fireMoveLeft(level);
                case 's' -> InputHandler.fireMoveDown(level);
                case 'd' -> InputHandler.fireMoveRight(level);
                case 'r' -> <call restart logic>;
                default   -> 
                    System.out.println("Invalid input. Use w/a/s/d to move.");
            }

        }
        scanner.close();
    }
}