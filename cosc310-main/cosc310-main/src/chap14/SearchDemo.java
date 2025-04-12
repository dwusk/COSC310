package chap14;

public class SearchDemo {
    public static void main(String[] args) {
        int airports[] = new int[] {0, 1, 2, 9, 25};
        System.out.println(Searcher.bfindFirst(0, airports));
        System.out.println(Searcher.bfindFirst(1, airports));
        System.out.println(Searcher.bfindFirst(2, airports));
        System.out.println(Searcher.bfindFirst(9, airports));
        System.out.println(Searcher.bfindFirst(25, airports));
        System.out.println(Searcher.bfindFirst(7, airports));

    }
}
