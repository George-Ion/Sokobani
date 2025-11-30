package com.george;

public class Wall extends GameObject {

    public Wall(Position location) {
        super(location);
    }

    @Override
    public String getSymbol() {
        return "#";
    }
    
}
