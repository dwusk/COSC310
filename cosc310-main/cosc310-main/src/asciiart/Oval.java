package asciiart;

public class Oval extends Shape {

    public Oval(int l, int w, String color, char c) {
        super(l, w, color, c);
    }

    @Override
    public double getArea() {
        return Math.PI*(w/2.0)*(l/2.0);
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*Math.sqrt((l*l+w*w)/2.0);
    }

    @Override
    public void draw() {
        super.draw();
        
    }

    @Override
    public String getType() {
        return "OVAL";
    }


    
}
