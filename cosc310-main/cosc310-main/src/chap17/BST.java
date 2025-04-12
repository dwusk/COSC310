package chap17;

public class BST<E> {
    // attributes
    protected BSTNode root;
    protected long size = 0;

    public BST() {
        // nothing to do here ... we don't have a root yet
    }

    public BST(E e) {
        this.root = new BSTNode(null, e);
        this.size = 1;
    }

    public BSTNode getRoot() {
        return root;
    }

    public long size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // basic algorithm ... search for the node and if the result is a placeholder
    // node, then the element is not in the tree AND you have exactly the node where
    // it belongs!
    public void add(E e) {
        BSTNode node = search(root, e); // use the helper method which returns the placeholder node
        if (node.e == null) {
            // we found the placeholder node where this new piece of data belongs
            // turn it into a real node by giving it some data AND two fake children
            node.e = e;
            node.left = new BSTNode(node, null, null, null);
            node.right = new BSTNode(node, null, null, null);
            size++;
        } else {
            // uh-oh that node was already in the tree
            // probably the worst thing we could do is silently ignore the request
            // so let's throw an exception
            // throw new IllegalArgumentException("Duplicate element: " + e);
        }
    }

    // recursive implementation
    // note the API call returns null if the element is not found
    // whereas the recursive implementation returns a placeholder node
    public BSTNode search(E e) {
        BSTNode hit = search(root, e);
        if (hit.e==null) {
            return null;
        }
        return hit;
    }

    protected BSTNode search(BSTNode n, E e) {
        if (n.e == null) {
            return n;
        }
        int comparison = ((Comparable<E>) e).compareTo(n.e);
        if (comparison == 0) {
            return n;
        } else if (comparison < 0) {
            return search(n.left, e);
        } else {
            return search(n.right, e);
        }
    }

    @Override
    public String toString() {
        return toString(root, 0);
    }
    protected String toString(BSTNode n, int indent) {
        if (n.e == null) {
            return " ".repeat(indent) + "null\n";
        }
        String s = " ".repeat(indent) + n.e + "\n";
        s += toString(n.left, indent + 2);
        s += toString(n.right, indent + 2);
        return s;
    }

    protected class BSTNode {
        // note that there are NO public getters or setters for these ... we don't want
        // to allow the BST to be corrupted
        // it is up to the BST class itself to manage the nodes and node relationships
        protected BSTNode parent;
        protected BSTNode left;
        protected BSTNode right;
        protected E e;

        public BSTNode(BSTNode parent, E e) {
            this.parent = parent;
            this.e = e;
            this.left = new BSTNode(this, null, null, null); // the third null is for the data which is null b/c it's
                                                                          // only a placeholder node
            this.right = new BSTNode(this, null, null, null);
        }

        // helpful when restructuring the tree
        public BSTNode(BSTNode parent, BSTNode left, BSTNode right, E e) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.e = e;
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

}
