public class Target extends GameObject{

    public Target(Position location) {
        super(location);
    }

    @Override
    public String getSymbol() {
        return "  T  ";
    }
    
}
