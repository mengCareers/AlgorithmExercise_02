package CompanyOriented.Facebook;

import CompanyOriented.Google.TreeNode;

import java.util.Stack;

/**
 * implement an iterator over a BST
 */

/**
 * Smallest => Left-most Leaf
 * e.g.
 * 7
 * /
 * 5
 * / \
 * 3   6
 * Since we don't have a father pointer, we need to add a pointer points to father
 * father pointer is not enough, we use a stack instead
 */
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

        TreeNode ptr = stack.pop();
        int val = ptr.val;

        if (ptr.right != null) {
            ptr = ptr.right;
            stack.push(ptr);
            ptr = ptr.left;
            while (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            }
        }

        return val;

    }

}
