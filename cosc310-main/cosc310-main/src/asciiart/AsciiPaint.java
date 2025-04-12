package asciiart;

import java.util.ArrayList;

public class AsciiPaint {
    public static void main(String[] args) {
        ArrayList<Shape> shapesList = new ArrayList<>();
        Shape[] shapesArray = new Shape[1000];
        int shapeCnt = 0;
        System.out.println(shapesList.size());
        System.out.println(shapesArray.length);
        
        // add some stuff to both data structures
        Rectangle r = new Rectangle(4, 6, "red", '*');
        shapesList.add(r);
        shapesArray[shapeCnt++] = r;
        Square s = new Square(5, "green", '-');
        shapesList.add(s);
        shapesArray[shapeCnt++] = s;
        s = new Square(5, "yellow", '+');
        shapesList.add(s);
        Triangle t = new Triangle(9, 7, "pink", '*');
        shapesList.add(t);
        shapesArray[shapeCnt++] = s;

        for (Shape shape : shapesList) {
            shape.draw();
        }
    }
}
