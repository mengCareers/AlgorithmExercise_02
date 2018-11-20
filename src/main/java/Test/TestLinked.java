package Test;

public class TestLinked {
    static class Node {
        Node next;
        int val;

        public Node(int val) {
            next = null;
            this.val = val;
        }
    }

    static public void reassignValue(Node root) {
        root = new Node(1);

    }

    public static void main(String[] args) {
        Node root = new Node(0);
        reassignValue(root);
        System.out.println(root.val);
    }
    // ref points sth
    // change ref itself, non-sense, e.g. ref = new Ref
    // change ref.val, makes sense

}
