package asciiart;

public abstract class Shape {

    protected int l;  // the length of our bounding box
    protected int w;  // the width of our bounding box 
    protected String color; // just going to display a message with this color
    protected char c; // the character we will draw with

    public Shape(int l, int w, String color, char c) {
        this.l = l;
        this.w = w;
        this.color = color;
        this.c = c;
    }

    abstract public double getArea();
    abstract public double getPerimeter();
    abstract public String getType();

    public void draw() {
        System.out.println("Imagine the " + getType() + " you are about to see is " + color);
    }

}
