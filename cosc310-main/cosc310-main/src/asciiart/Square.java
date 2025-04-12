package asciiart;

public class Square extends Rectangle {

    public Square(int sidelength, String color, char c) {
        super(sidelength, sidelength, color, c);
        
    }

    @Override
    public String getType() {
        return "SQUARE";
    }
    
}
