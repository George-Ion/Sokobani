public class Player {

    private Position currPosition;
    private int gridWidth;
    private int gridHeight;

    /*
     * Build the Player constructor which will taking the starting position using the Position class and the grid Width and height so the player stays inside the grid.
     */
    public Player(Position startingPosition, int gridHeight, int gridWidth) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        currPosition = startingPosition;
    }
    /*
     * Following is the moves that a player can do with constrains in them regarding the grid.
     */
    public void moveUp() {
        if (currPosition.getY() - 1 >= 0) {
            currPosition.setY(currPosition.getY() - 1);
        }
        else {
            System.out.println("Cant move, out of grid");
        }
    }

    public void moveDown() {
        if (currPosition.getY() + 1 < gridHeight) {
            currPosition.setY(currPosition.getY() + 1);
        } 
        else {
            System.out.println("Cant move, out of grid");
        }
    }
    
    public void moveLeft() { 
        if (currPosition.getX() - 1 >= 0) {
            currPosition.setX(currPosition.getX() - 1);
        }
        else {
            System.out.println("Cant move, out of grid");
        }
    }
    
    public void moveRight() {
        if (currPosition.getX() + 1 < gridWidth) {
            currPosition.setX(currPosition.getX() + 1);
        }
        else {
            System.out.println("Cant move, out of grid");
        }

    }

    /*
     * Method Getposition help us get the current position of the player
     */
    public Position GetPosition() {
        return currPosition;
    }
}
