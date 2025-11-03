public class Game {
    public static void main(String[] args) {
        /* 
         * Starting the Game!
         */
        System.out.println("Sokobani starts!");

        /*
         * Initiallizing the position of the player.
         */
        Position playerPosition = new Position(2,2 );

        System.out.println(playerPosition.toString());

        /*
         * Initiallizing the player and the height and length of the grid.
         */
        Player player1 = new Player(playerPosition,5,5);

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
        player1.moveDown();


        /*
         * Looping and creating the grid and getting our player displayed on to it as P.
         */
        for( int y = 0; y < 5; y++ ) {
            System.out.println("");
            for( int x = 0; x < 5; x++ ){

                if (player1.GetPosition().getX() == x && player1.GetPosition().getY() == y) {
                    System.out.print("  P  ");
                }
                else {
                    System.out.print("  .  ");
                }
            }
        }

    }
}
