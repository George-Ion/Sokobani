package com.george;

public class Player extends GameObject implements Movable{

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
     * Creating getWidth and getHeight to be able t pass it to our loop in main.
     */
    public int getWidth() {
        return this.gridWidth;
    }

    public int getHeight() {
        return this.gridHeight;
    }

    public void setDimensions(int newHeight, int newWidth) {
        this.gridHeight = newHeight;
        this.gridWidth = newWidth;
    }

    @Override
    public String getSymbol() {
        return "P" ;
    }

    /*
    Removed all move methods to pass it through the implemented move method.
    */
    @Override
    public void move(int dx, int dy) {
        
        Position position = getPosition();

        int new_x = position.getX() + dx;
        int new_y = position.getY() + dy;

        if (new_x < 0 || new_x >= gridWidth || new_y < 0 || new_y >= gridHeight)  {
            System.out.println("Cant move, out of grid!");
            return ;
        }

        position.setX(new_x);
        position.setY(new_y);
    }
}


