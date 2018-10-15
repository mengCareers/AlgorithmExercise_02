package CompanyOriented.Pinterest;

import CompanyOriented.Google.TreeNode;

/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of
the longest path between any two nodes in a tree. This path may or may not pass through the root.
 */
/*
asked to get longest path between any two nodes
divide and conquer
null, 0
1, 0
 1
/
2  1
 1
2 3  1+1

    1
   /\
  2 3
 /
4            2 + 1
if pass root, max(dist(left), dist(right)) + 1
if not, max(func(left), func(right))
 */
public class DiameterOfBT {
    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;

        if (root == null)
            return 0;

        getHeight(root);
        return diameter;
    }

    private int getHeight(TreeNode root) {

        if (root == null)
            return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        diameter = Math.max(diameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
