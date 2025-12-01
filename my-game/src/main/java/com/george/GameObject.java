package com.george;

public abstract class GameObject {
    
    private Position location;
    /*
     * Creating the constructor of GameObject so it takes the location of the object.
     */
    public GameObject(Position location){
        this.location = location ;
    }

    /*
     * Method to get the location of the object.
     */
    public Position getPosition() {
        return location;
    }

    /*
     * Abstract method because the object can vary.
     */
    public abstract String getSymbol();

    /*
    * Creating isAt method to check the position of the GameObjects 
    */
    public boolean isAt(int x , int y) {
        return location.getX() == x && location.getY() == y ;
    }
}
