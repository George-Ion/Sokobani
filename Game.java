import java.util.*;

public class Game {
    public static void main(String[] args) {
        /* 
         * Starting the Game!
         */
        System.out.println("Sokobani starts!");

        /*
         * Creating the List<GameObject> is like saying "I only care that this variable acts like a List and holds GameObjects."
         * ArryList<GameObject> calls the constructor of the ArrayList class to set up the list structure in memory, ready to accept objects of type GameObject.
         */
        List<GameObject> objects = new ArrayList<GameObject>();

        /*
         * Initiallizing the position of the player.
         */
        Position playerPosition = new Position(2 , 2 );

        /*
         * Initiallizing player target and box.
         */
        Player player1 = new Player(playerPosition,7,9);

        Position targetPosition = new Position(1, 1);

        Target target1 = new Target(targetPosition);

        Position boxPosition = new Position(3, 3);

        Box box1 = new Box(boxPosition);

        /*
         * Implementing a for loop to create the wall border of the game.
         */
        for(int i = 0 ; i < player1.getWidth() ; i++ ) {
            Position wall1 = new Position(i, 0);
            Wall topWall = new Wall(wall1);
            objects.add(topWall);
            Position wall2 = new Position(0, i);
            Wall leftWall = new Wall(wall2);
            objects.add(leftWall);
            Position wall3 = new Position(i, player1.getHeight() - 1 );
            Wall bottomWall = new Wall(wall3);
            objects.add(bottomWall);
            Position wall4 = new Position(player1.getWidth() - 1 , i);
            Wall righttWall = new Wall(wall4);
            objects.add(righttWall);
        }

        objects.add(target1) ;
        objects.add(player1) ;
        objects.add(box1) ;


        /*
         * Moving the player and trying to move the player out of the grid.
         */
        player1.moveUp();
        player1.moveRight();
        player1.moveRight();
        player1.moveDown();
        player1.moveDown();
        player1.moveLeft();
        player1.moveDown();

        /*
         * Looping and creating the grid and getting our player displayed on to it as P.
         */
        for( int y = 0; y < player1.getHeight(); y++ ) {
            System.out.println("");
            for( int x = 0; x < player1.getWidth(); x++ ){

                GameObject foundObject = findGameObject(x, y, objects);

                if (foundObject != null) {
                    System.out.print(foundObject.getSymbol());
                }
                else {
                    System.out.print("  .  ");
                }
            }
        }
    }

    /*
    * Creating a helper method to find if object isAt the exact position
    */
    public static GameObject findGameObject(int x, int y, List<GameObject> objects) {
        for (GameObject obj : objects)
                if (obj.isAt(x, y)) {
                    return obj;
                }
        return null;
    }
}