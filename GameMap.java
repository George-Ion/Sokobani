import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//Transfered all the game logic in GameMap class instead of our main Game class so our main has only the role to start the game
public class GameMap {

    private List<GameObject> staticObj = new ArrayList<>();
    private List<GameObject> dynamicObj = new ArrayList<>();
    private int width ; 
    private int height ; 
    private Player player;

    public GameMap() {

    }
    
    //Map builder 
    private void draw() {
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < width; x++) {
                GameObject obj = findGameObject(x, y);
                row.append((obj != null ? obj.getSymbol() : '.') + " ");
            }
            System.out.println(row);
        }
    }

    public GameObject findGameObject(int x, int y) {
        //Checking for dynamic Objects first so the box or a player can be on top of a Target.
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
            // Logic changed so boxes only stop when there is a wall behind
            if (findGameObject( new_x + dx, new_y + dy) instanceof Wall) {
                return ;
            }
            ((Box) foundObject).move(dx, dy);
            player.move(dx, dy);
        }      
    }

    public void update(){
        draw();
        checkWinCondition();
    }

    public void checkWinCondition() {
        //each time the player moves
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
            System.exit(0);
        }
    }
    

    // Read the level line by line and and create the coordinates of all the objects
    public void loadFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int y = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (y == 0) {
                    this.width = line.length();
                }

                for (int x = 0 ; x < line.length() ; x++) {
                    String character = String.valueOf(line.charAt(x));
                    switch (character) {
                        case "P":
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
                        default:
                            break;
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
        }catch (IOException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }
}
