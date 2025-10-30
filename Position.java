public class Position {

    private int x_coordinate;
    private int y_coordinate;

    public Position(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

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

    public String toString() {
        return "("+ x_coordinate + "," + y_coordinate + ")";
    }

}
