public class Player extends GameObject{

    private int gridWidth;
    private int gridHeight;

    /*
     * Build the Player constructor which will taking the starting position coming from the superclass GameObject using the Position class and the grid Width and height so the player stays inside the grid.
     */
    public Player(Position startingPosition, int gridHeight, int gridWidth) {
        super(startingPosition) ;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }
    /*
     * Following is the moves that a player can do with constrains in them regarding the grid.
     * Change the currPosition with 
     */
    public void moveUp() {
        if (getPosition().getY() - 1 >= 0) {
            getPosition().setY(getPosition().getY() - 1);
        }
        else {
            System.out.println("Cant move, out of grid!");
        }
    }

    public void moveDown() {
        if (getPosition().getY() + 1 < gridHeight) {
            getPosition().setY(getPosition().getY() + 1);
        } 
        else {
            System.out.println("Cant move, out of grid!");
        }
    }
    
    public void moveLeft() { 
        if (getPosition().getX() - 1 >= 0) {
            getPosition().setX(getPosition().getX() - 1);
        }
        else {
            System.out.println("Cant move, out of grid!");
        }
    }
    
    public void moveRight() {
        if (getPosition().getX() + 1 < gridWidth) {
            getPosition().setX(getPosition().getX() + 1);
        }
        else {
            System.out.println("Cant move, out of grid!");
        }
    }

    /*
     * Creating getWidth and getHeight to be able t pass it to our loop in main.
     */
    public int getWidth() {
        return gridWidth;
    }

    public int getHeight() {
        return gridHeight;
    }

    @Override
    public String getSymbol() {
        return "  P  " ;
    }
}


