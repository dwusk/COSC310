package asciiart;

public class Rectangle extends Shape {

    public Rectangle(int l, int w, String color, char c) {
        super(l, w, color, c);
    }

    @Override
    public double getArea() {
        return l*w;
    }

    @Override
    public double getPerimeter() {
        return 2*l+2*w;
    }

    @Override
    public void draw() {
        super.draw();
        String topbottom = (""+c).repeat(w);
        String middle = c + " ".repeat(w-2) + c;
        System.out.println(topbottom);
        for(int i=0; i<l-2; i++) {
            System.out.println(middle);
        }
        System.out.println(topbottom);
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }



    
}
