public class Position {

    private int x_coordinate = 0;
    private int y_coordinate = 0;

    /*
     * Contructor of the position gets our x and y variables
     */
    public Position(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    /*
     * Creating getters and Setters methods so that our other classes can interact with the position.
     */
    public int getX() {
        return x_coordinate;
    }

    public int getY() {
        return y_coordinate;
    }

    public void setX(int newX) {
        x_coordinate = newX;
    }

    public void setY(int newY) {
        y_coordinate = newY;
    }

    /*
     * Transforming the coordiantes in a textual representation.
     */
    public String toString() {
        return "("+ x_coordinate + "," + y_coordinate + ")";
    }

}
