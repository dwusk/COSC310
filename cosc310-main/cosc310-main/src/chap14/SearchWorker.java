package chap14;

public class SearchWorker implements Runnable {
    private int i;
    private int j;
    private int needle;
    private int haystack[];
    private int result;

    public SearchWorker(int i, int j, int needle, int haystack[]) {
        this.i = i;
        this.j = j;
        this.needle = needle;
        this.haystack = haystack;
    }

    public int searchHelper(int i, int j, int needle, int haystack[]) {
        for (int k=i; k<j; k++) {
            if (needle==haystack[k]) {
                return k;
            }
        }
        return -1;
    }

    public void run() {
        result = searchHelper(i, j, needle, haystack);
    }

    public int getResult() {
        return result;
    }
}
