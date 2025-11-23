import java.util.*;

public class Game {
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

            int dx = 0, dy = 0;
            switch (input) {
                case "w":
                    dy = -1;
                    break;
                case "s":
                    dy = 1;
                    break;
                case "a":
                    dx = -1;
                    break;
                case "d":
                    dx = 1;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            if (dx != 0 || dy != 0) {
                level.movePlayer(dx, dy);
            }
        }
        scanner.close();
    }
}