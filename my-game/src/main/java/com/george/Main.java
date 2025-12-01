package com.george;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* 
         * Starting the Game!
         */
        System.out.println("Sokobani starts!");

        GameMap level = new GameMap();

        try {
            level.loadFromFile("level1.txt");
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR: Could not load level!");
            return; 
        }
        
        /*
         * Implemention of the Scanner object so the movement can be done through the keyboard
         */
        InputHandler handler = new InputHandler();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            level.update();
            System.out.print("Enter move (w/a/s/d, q=quit): ");
            String input = scanner.nextLine();
            if (input.equals("q"))
                break;
            
            // implemented save and load features 
            if (input.startsWith("save")) {
                String[] parts = input.split(" ");
                String filename;
                if (parts.length < 2){
                    filename = "save.txt" ;
                }
                else {
                    filename = parts[1];
                }
                try {
                    level.writeToFile(filename);
                    System.out.println("Game saved to " + filename);
                } catch (Exception e) {
                    System.out.println("Save failed: " + e.getMessage());
                }
                continue ;
            }
            else if (input.startsWith("load")) {
                String[] parts = input.split(" ");
                String filename;
                if (parts.length < 2) {
                   filename = "save.txt";
                }
                else {
                    filename = parts[1];
                }
                try {
                    level.loadFromFile(filename);
                    System.out.println("Game loaded from " + filename);
                } catch (Exception e) {
                    System.out.println("Load failed: " + e.getMessage());
                }
                continue ;
            }


            if (input.length() < 1) continue;

            char c = Character.toLowerCase(input.charAt(0));

            switch (c) {
                case 'w' -> InputHandler.fireMoveUp(level);
                case 'a' -> InputHandler.fireMoveLeft(level);
                case 's' -> InputHandler.fireMoveDown(level);
                case 'd' -> InputHandler.fireMoveRight(level);
                case 'r' -> {
                    System.out.println("Restarting level...");
                    /*
                    *Even though level1.txt existed at the start, something might have 
                     * happened to it while playing. We catch errors here to prevent a crash during restart.
                     */
                    try {
                        level.loadFromFile("level1.txt");
                    } catch (Exception e) {
                        System.out.println("Restart failed: " + e.getMessage());
                    }
                }
                default   -> 
                    System.out.println("Invalid input. Use w/a/s/d to move.");
            }

            level.writeToFile("autosave.txt");

        }
        scanner.close();
    }
}