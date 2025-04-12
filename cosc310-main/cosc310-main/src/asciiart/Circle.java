package asciiart;

public class Circle extends Oval {

    public Circle(int sidelength, String color, char c) {
        super(sidelength, sidelength, color, c);
        
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }
    
}
