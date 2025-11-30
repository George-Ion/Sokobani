package com.george;

public class Box extends GameObject implements Movable {

    public Box(Position location) {
        super(location);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    /*
    Box will move only by dx and dy . The check for box colission will be in the Game class
     */
    @Override
    public void move(int dx, int dy) {
        Position position = getPosition();
        position.setX(position.getX() + dx);
        position.setY(position.getY() + dy);
    }

}
