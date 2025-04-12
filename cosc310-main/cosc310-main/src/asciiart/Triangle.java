package asciiart;

public class Triangle extends Shape {

    public Triangle(int l, int w, String color, char c) {
        super(l, w, color, c);
    }

    @Override
    public double getArea() {
        return l*w*0.5;
    }

    @Override
    public double getPerimeter() {
        double c = Math.sqrt(l*l+(w/2.0)*(w/2.0));
        return 2*c+w;
    }

    @Override
    public void draw() {
        super.draw();
        String bottom = (""+c).repeat(w);
        int stackHeight = Math.round(l/(w/2.0f));        
        if (w%2 == 0) {
            // handle even case here
            for (int spaces=0; spaces<w/2; spaces += 2)
                for (int i=0; i<stackHeight; i++)
                    System.out.println(c+" ".repeat(spaces)+c);
        } else {
            // handle odd case
            for (int i=0; i<stackHeight; i++)
                System.out.println(" ".repeat(w/2)+c);
            for (int spaces=1; spaces<w/2; spaces += 2)
                for (int i=0; i<stackHeight; i++) {
                    String middle = c+" ".repeat(spaces)+c;
                    int ospaces = (w-middle.length())/2;
                    System.out.println(" ".repeat(ospaces)+middle);
                }
        }
        System.out.println(bottom);
    }

    @Override
    public String getType() {
        return "TRIANGLE";
    }



    
}
