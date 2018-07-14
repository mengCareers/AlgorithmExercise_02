package Contest;

import Google.TreeNode;

/**
 * LCA of p, q
 * for each step,
 * we try to search in left_subtree or right_subtree
 */
public class LowestCommonAncestorOfBT {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode leftSub = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSub = lowestCommonAncestor(root.right, p, q);

        if (leftSub == null) { // If p, q are both to the right of root
            return rightSub;
        }
        if (rightSub == null) { // If p, q are both to the left of root,
            return leftSub;
        }
        return root; // If p, q locate different sides of root,

    }
}
