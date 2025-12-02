package com.george;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;


//Transfered all the game logic in GameMap class instead of our main Game class so our main has only the role to start the game
public class GameMap {

    private List<GameObject> staticObj = new ArrayList<>();
    private List<GameObject> dynamicObj = new ArrayList<>();
    private int width ; 
    private int height ; 
    private Player player;

    public GameMap() {

    }
    /**
     * Draws the map, but checks for overlaps (Box on Target) to print special symbols.
     */ 
    private void draw() {
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < width; x++) {
                
                // Find the top object 
                GameObject topObj = findGameObject(x, y);
                
                // Check if there is also a target in the position
                boolean hasTarget = false;
                for (GameObject staticObj : staticObj) {
                    if (staticObj instanceof Target && staticObj.isAt(x, y)) {
                        hasTarget = true;
                        break;
                    }
                }

                //  deciding on what to print 
                char symbol;
                if (topObj == null) {
                    //we need to print the target if it is there
                    symbol = hasTarget ? 'T' : ' '; 
                } else if (hasTarget && topObj instanceof Box) {
                    symbol = '*'; 
                } else if (hasTarget && topObj instanceof Player) {
                    symbol = '+'; 
                } else {
                    symbol = topObj.getSymbol().charAt(0); 
                }

                row.append(symbol + " ");
            }
            System.out.println(row);
        }
    }

    /**
     * Looks up the top-most object at a specific grid coordinate.
     * This ensures that if a Box is sitting on a Target, this method returns the Box,
     * allowing the game to treat that tile as "occupied" for collision purposes.
     */
    public GameObject findGameObject(int x, int y) {
        for (GameObject obj : dynamicObj)  
                if (obj.isAt(x, y)) {
                    return obj;
                }
        for (GameObject obj : staticObj)  
            if (obj.isAt(x, y)) {
                return obj;
            }
        return null;
    }

    /**
     * Attempts to move the player in the specified direction.
     * Handles collisions with Walls and logic for pushing Boxes.
     * @param dx The change in X (e.g., 1 for right, -1 for left).
     * @param dy The change in Y (e.g., 1 for down, -1 for up).
     */
    public void movePlayer(int dx, int dy){
        int new_x = player.getPosition().getX() + dx ;
        int new_y = player.getPosition().getY() + dy ; 
        GameObject foundObject = findGameObject(new_x, new_y) ;

        if ((foundObject == null) || (foundObject instanceof Target)) {
           player.move(dx, dy);
        }
        else if (foundObject instanceof Wall) {
            return ;
        }
        else if (foundObject instanceof Box) {
            // Logic changed so boxes only stop when there is a wall behind, so they freely move on top of targets.
            if (findGameObject( new_x + dx, new_y + dy) instanceof Wall) {
                return ;
            }
            ((Box) foundObject).move(dx, dy);
            player.move(dx, dy);
        }      
    }

    /**
     * Central update method called after every input action.
     */
    public void update(){
        draw();
        checkWinCondition();
    }

    /**
     * Checks if the victory condition has been met. 
     * The player wins if EVERY Target on the map has a Box currently sitting on it.
     */
    public void checkWinCondition() {
        int numberOfTargets = 0;
        int numberOfBoxes = 0;
        for (GameObject object : staticObj) {
            if (object instanceof Target) {
                numberOfTargets ++ ;
                Position targetPosition = object.getPosition();
                for (GameObject object2 : dynamicObj) {
                    if ((object2 instanceof Box) && (object2.getPosition().equals(targetPosition))) {
                        numberOfBoxes ++ ;
                    }
                }
            }
        }
        if ((numberOfTargets == numberOfBoxes) && (numberOfTargets > 0)) {
            System.out.println("YOU WIN!!!");
            // Game will stop after the game is won
            System.exit(0);
        }
    }
    

    /**
     * Read the level line by line and and create the coordinates of all the objects
     * Switching to streams so i can work with the levels from resources
     * This method prioritizes local files for saved games 
     * If a local file is not found, it attempts to load from the classpath resources 
     * @throws Exception If the file cannot be found or the format is invalid.
    */ 
    public void loadFromFile(String filename) throws Exception {
        //clean objects so we if we load new map we dont have duplicated values
        staticObj.clear();
        dynamicObj.clear();

        int playercount = 0;

        Scanner scanner = null;
        File file = new File(filename);
        
        // Determining the source of the file 
        if (file.exists()) {
            // Found it on disk this works for saved games
            scanner = new Scanner(file);
        } else {
            // Didn't find on disk, check resources 
            InputStream stream = getClass().getResourceAsStream("/" + filename);
            if (stream == null) {
                throw new IOException("Could not find file: " + filename);
            }
            scanner = new Scanner(stream);
        }

        int y = 0;

        // we use try to ensure try closes automatically 
        try (Scanner s = scanner) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Created the if so the grid width will not need to update through each loop
                if (y == 0) {
                    this.width = line.length();
                }
                if (line.length() != width) {
                    throw new InvalidLevelFormatException("Invalid level format! All lines must have the same length");
                }

                for (int x = 0 ; x < line.length() ; x++) {
                    
                    String character = String.valueOf(line.charAt(x));

                    switch (character) {
                        case "P":
                            playercount++ ;
                            Position playerPosition = new Position(x, y);
                            Player player = new Player(playerPosition, this.height, this.width);
                            dynamicObj.add(player);
                            break;
                        case "#":
                            Position wallPosition = new Position(x, y);
                            Wall wall = new Wall(wallPosition);
                            staticObj.add(wall);
                            break;
                        case "T":
                            Position targetPosition = new Position(x, y);
                            Target target = new Target(targetPosition);
                            staticObj.add(target);
                            break;
                        case "B":
                            Position boxPosition = new Position(x, y);
                            Box box = new Box(boxPosition);
                            dynamicObj.add(box);
                            break;
                            // message for saved games
                        case "*": 
                            // creating 2 different positions with the same coordinates so box and target are not indicating int the same place in memory and becoming just a star symbol
                            Position posTar = new Position(x, y);
                            Position posBox = new Position(x , y);
                            staticObj.add(new Target(posTar));
                            dynamicObj.add(new Box(posBox));
                            break;
                        case "+": 
                            // creating two variables with the same logic as in case : *
                            Position posPlay = new Position(x, y);
                            Position posTarg = new Position(x, y);
                            staticObj.add(new Target(posTarg));
                            playercount++;
                            dynamicObj.add(new Player(posPlay, this.height, this.width));
                            break;
                        case " ":
                            break;
                        default:
                            throw new InvalidLevelFormatException("Invalid character in the level at line " + y + " and row " + x);
                    }
                }
                y++ ;
            
            }
            //After looping through all the lines we are certain about the height of the level,
            // the width we assume it is equal to the width of a line
            this.height = y ;
            for (GameObject obj : this.dynamicObj) {
                if (obj instanceof Player) {
                    this.player = (Player) obj;
                    this.player.setDimensions(this.height, this.width);
                    break;
                }
            }
            //validating we only have one player
            if (playercount != 1) {
                throw new InvalidLevelFormatException("Game should exactly have one player");
            }
        }
    }

    /**
     * Saving the current map state to be able to do autosave 
     * @param filename
     */
    public void writeToFile(String filename) {
        // need a 2D string because the map is 2D
        String[][] grid = new String[this.height][this.width];
        // itteration through the map just to fill the 2D array
        for (int y = 0 ; y < this.height ; y++) {
            for (int x = 0 ; x < this.width ; x++) {
                grid[y][x] = " " ;
            }
        }
        // first we place the static objects 
        for (GameObject statObject : staticObj) {
            Position statPosition = statObject.getPosition();
            int statX = statPosition.getX();
            int statY = statPosition.getY();
            String Symbol = statObject.getSymbol();
            grid[statY][statX] = Symbol ;
        }

        // we then place in our array the dynamic objects with cheking if P , B are on the same 
        // position as a Target and using the right symbol + or *
        for (GameObject dynObj : dynamicObj) {
            int x = dynObj.getPosition().getX();
            int y = dynObj.getPosition().getY();
            
            boolean onTarget = false;
            for (GameObject staticObj : staticObj) {
                if (staticObj instanceof Target && staticObj.isAt(x, y)) {
                    onTarget = true;
                    break;
                }
            }

            if (onTarget) {
                if (dynObj instanceof Box) {
                    grid[y][x] = "*"; 
                } else {
                    grid[y][x] = "+"; 
                }
            } else {
                grid[y][x] = dynObj.getSymbol(); 
            }
        }

        // Saves it to the file
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filename))) {
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    writer.write(grid[y][x]);
                }
                writer.newLine(); 
            }
        } catch (java.io.IOException e) {
            System.out.println("Autosave failed: " + e.getMessage());
        }
    }
}


