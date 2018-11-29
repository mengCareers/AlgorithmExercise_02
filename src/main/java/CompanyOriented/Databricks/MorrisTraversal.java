package CompanyOriented.Databricks;

import CompanyOriented.Google.TreeNode;

public class MorrisTraversal {
    public void inorderTraverse(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {

                /* Find inorder predecessor of curr, the rightmost one on the left subtree. */
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {

                    /* Create temporary link. */
                    predecessor.right = curr;

                    curr = curr.left;
                } else {

                    /* Traversed curr's left subtree already. */
                    predecessor.right = null;
                    visit(curr);
                    curr = curr.right;
                }
            }
        }
    }

    public void preorderTraverse(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                visit(curr);
                curr = curr.right;
            } else {

                /* Find inorder predecessor of curr, the rightmost one on the left subtree. */
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {

                    /* Create temporary link. */
                    predecessor.right = curr;
                    visit(curr);
                    curr = curr.left;
                } else {

                    /* Traversed curr's left subtree already. */
                    predecessor.right = null;
//                    visit(curr);
                    curr = curr.right;
                }
            }
        }
    }

    private static void visit(TreeNode node) {
        System.out.println("Visit " + node.val);
    }
}
