package chap6;

// a really bad version of ArrayList b/c we are too lazy to have a second variable which keeps track of the real size
public class ResizableArray<T> {

    protected T[] data; // this is where the actual data is stored

    @SuppressWarnings("unchecked")
    public ResizableArray() {
       data = (T[]) new Object[0];
    }

    /**
     * adds item e to the end of the array ... always and forever
     * @param e - the new data to be added
     */
    @SuppressWarnings("unchecked")
    public void add(T e) {
        T[] temparray = (T[]) new Object[data.length+1];
        int i=0;
        for (; i<data.length; i++) {
            temparray[i] = data[i];
        }
        temparray[i] = e;
        data = temparray;
    }

    /**
     * deletes item at index i
     * @param i - the index of the item to be deleted
     * @return count of how many item(s) were deleted ... pushes the burden of checking the param on the coder
     */
    @SuppressWarnings("unchecked")
    public void delete(int i) {
        if (i >= data.length) {
            // return; // one bad way to handle invalid param/out of bounds position is to simply ignore it ... bad, bad, bad ... ignore the problem maybe it will go away
            throw new IndexOutOfBoundsException("index out of bounds"); // this doesn't give the coder a chance to avoid the problem or miss the problem
        }
// if you are deleting the one indicated below
//               |
//               |
//               v
//        [3, 4, 5, 6, 7, 8]  (this is before the delete)
//        [3, 4, 6, 7, 8] (this is after the delete)
// then you gotta do all this:
//
 
        T[] temparray = (T[]) new Object[data.length-1];

        // preserve all the items before the doomed item by copying them into the new array
        for (int j=0; j<i; j++) {
            temparray[j] = data[j];
        }

        // shift all the items after i over one spot in the new array 
        for (int k=i+1; k<=temparray.length; k++) {
            temparray[k-1] = data[k];
        }

        data = temparray;
    }

    public int length() {
        return data.length;
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(data);
    }

}
