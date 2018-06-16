package Google;

import java.util.Stack;

/*
an iterator over a BST
initialized with the root
next() will return the next smallest number ( MIN HEAP not O(h) )
O(h), h is the height of the tree,

the smallest number in BST is the leftmost node of the tree lm,
how about the next? lm.parent
then? lm.parent.right.leftmost ..
how do we get the parent of lm? we cannot, but we can store nodes in a stack when we traverse to the lm.

* */
public class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode ptr = root;
        while (ptr != null) {
            stack.push(ptr);
            ptr = ptr.left;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode tmp = stack.pop();
        TreeNode ptr = tmp;
        if (ptr.right != null) {
            ptr = ptr.right;
            while (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            }
        }
        return tmp.val;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
