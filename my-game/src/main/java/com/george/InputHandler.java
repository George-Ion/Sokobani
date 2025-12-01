package com.george;

public class InputHandler {


    public static void fireMoveUp(GameMap map) {
        int dy = -1 ;
        map.movePlayer(0, dy);
    }


    public static void fireMoveDown(GameMap map) {
        map.movePlayer(0, 1);
    }


    public static void fireMoveLeft(GameMap map) {
        map.movePlayer(-1, 0);
    }


    public static void fireMoveRight(GameMap map) {
        map.movePlayer(1, 0);
    }

}
