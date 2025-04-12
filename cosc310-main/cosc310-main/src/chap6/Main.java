package chap6;

public class Main {
    public static void main(String[] args) {
        ResizableArray<Integer> myResizableArray = new ResizableArray<>();
        myResizableArray.add(5);
        myResizableArray.add(6);
        myResizableArray.add(7);
        myResizableArray.add(8);
        System.out.println(myResizableArray);
        try {
            myResizableArray.delete(4);
            System.out.println(myResizableArray);
        } catch (IndexOutOfBoundsException ex) {
            // oh well something must have gone wrong, i'll just let my program keep going but at least i'm going to log it! 
            System.err.println("LOG: something went wrong, accessed position 4 in array of length: " + myResizableArray.length());
        } finally {
            System.out.println(myResizableArray);
        }
        myResizableArray.delete(0);
        System.out.println(myResizableArray);
        myResizableArray.delete(2);
        System.out.println(myResizableArray);
        myResizableArray.delete(0);
        System.out.println(myResizableArray);
        myResizableArray.delete(0);
        System.out.println(myResizableArray);
        try {
            myResizableArray.delete(0);
        } catch (IndexOutOfBoundsException ex) {
            // oh well something must have gone wrong, i'll just let my program keep going but at least i'm going to log it! 
            System.err.println("LOG: something went wrong, accessed position 0 in array of length: " + myResizableArray.length());
        }
   }
}
