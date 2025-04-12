package chap17;

import java.util.ArrayList;
public class BTree<E> {
    
    // attribute section ... only one single attribute ... the root!
    protected BTreeNode root;

    protected class BTreeNode {
        // attributes
        protected BTreeNode parent;
        protected BTreeNode left;
        protected BTreeNode right;
        protected E data;
        
        public BTreeNode(BTreeNode parent, E data) {
            this.parent = parent;
            this.data = data;
        }
        public void setLeft(BTreeNode newchild) {
            this.left = newchild;
            if (newchild != null) {
                newchild.parent = this;
            }
        }
        public BTreeNode setLeft(E e) {
            this.left = new BTreeNode(this, e);
            return this.left;
        }
        public void setRight(BTreeNode newchild) {
            this.right = newchild;
            if (newchild != null) {
                newchild.parent = this;
            }
        }        
        public BTreeNode setRight(E e) {
            this.right = new BTreeNode(this, e);
            return this.right;
        }

        @Override
        public String toString() {
            String nodestrrep = data.toString();
            nodestrrep += parent==null ? "ROOT" : depth() + " DEEP";
            return nodestrrep;
        }

        protected int depth() {
            if (parent==null) {
                return 1;
            } else {
                return 1+parent.depth();
            }
        }
    }

    public BTree() {
        // nothing to do here ... we don't have a root yet
    }

    public BTree(E rootdata, BTree<E> leftside, BTree<E> rightside) {
        root = new BTreeNode(null,rootdata);
        root.left = leftside != null ? leftside.root : null;
        root.right = rightside != null ? rightside.root : null;
    }

    public BTreeNode root() {
        return root;
    }

    // the main size() method for the entire BTree
    public long size() {       
        return root==null?0:size(root);
    }

    // the size of the subBTree rooted at n
    //    is equal to 1 + the size of the subTree rooted at left + the size of the subTree rooted at right
    public long size(BTreeNode n) {
        long nsize = 1;
        if (n.left != null) {
            nsize += size(n.left);
        }
        if (n.right != null) {
            nsize += size(n.right);
        }
        return nsize;
    }

    /**
     * 
     * @param e - the data we are searching for
     * @return first node containing the data if found otherwise null
     */
    public BTreeNode search(E e) {
        return root==null ? null : search(e, root);
    }

    // DFS helper, but also useful for API ... starting your search at a particular node
    public BTreeNode search(E e, BTreeNode n) {
        if (n.data.equals(e)) {
            return n;
        }
        BTreeNode hit = null;
        if (n.left != null) {
            hit = search(e, n.left);
        }
        if (hit != null && n.right != null) {
            hit = search(e, n.right);
        }        
        return hit;
    }

    // alternative search method that uses BFS instead of DFS
    public BTreeNode searchBFS(E e) {
        ArrayList<BTreeNode> bfsqueue = new ArrayList<>();
        bfsqueue.add(root);
        return searchBFS(e, bfsqueue);
    }

    // BFS helper, but NOT useful for API b/c how do you know what "queue" to pass in?
    protected BTreeNode searchBFS(E e, ArrayList<BTreeNode> bfsqueue) {        
        if (bfsqueue.isEmpty()) {
            return null;
        }
        BTreeNode n = bfsqueue.remove(0);
        if (n.data.equals(e)) {
            return n;
        }
        if (n.left != null) {
            bfsqueue.add(n.left);
        }
        if (n.right != null) {
            bfsqueue.add(n.right);
        }
        return searchBFS(e, bfsqueue);
    }

    // public long height() {
    //     return root==null?0:height(root, 0);
    // }

    // public long height(BTreeNode n, long h) {

    // }

    @Override
    public String toString() {
        return size()==0?"EMPTY":toString(root, 0);
    }

    public String toString(BTreeNode n, int indent) {
        String spacestring = " ".repeat(indent);
        String result = spacestring + n.data.toString();

        if (n.left != null) {
            result += "\n" + toString(n.left, indent+2);
        }
        if (n.right != null) {
            result += "\n" + toString(n.right, indent+2);
        }

        return result;
    }



}
