package DivideAndConquer;

/**
 * The problem can be decreased to subproblems that are easy to solve.
 * base case: null          return
 * cur           cur.left = prev;
 * /             prev. right = cur
 * prev
 * <p>
 * cur           cur.left = prev;
 * / \           prev. right = cur
 * prev nxt        cur.right = nxt;
 * nxt.left = cur;
 * <p>
 * to reduce problem to subproblem:
 * Inorder traversal will build the subproblem above naturally.
 * and if we set prev = cur at the third step, we don't need the pointer nxt
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

public class ConvertBSTToSortedDoublyLinkedList {
    Node prev;

    public Node treeToDoublyList(Node root) {

        if (root == null) {
            return null;
        }

        Node dummyNode = new Node(0, null, null);
        prev = dummyNode;
        inorderTraversal(root);
        dummyNode.right.left = prev;
        prev.right = dummyNode.right;

        return dummyNode.right;
    }

    private void inorderTraversal(Node cur) {

        if (cur == null) {
            return;
        }

        inorderTraversal(cur.left);

        prev.right = cur;
        cur.left = prev;
        prev = cur;

        inorderTraversal(cur.right);
    }
}
