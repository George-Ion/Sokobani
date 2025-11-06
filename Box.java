public class Box  extends GameObject {

    public Box(Position location) {
        super(location);
    }

    @Override
    public String getSymbol() {
        return "  B  ";
    }
    
}
