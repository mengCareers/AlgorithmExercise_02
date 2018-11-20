package CompanyOriented.Pinterest;

import CompanyOriented.Google.TreeNode;

/*
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.
 */
public class BinaryTreeMaxPathSum {
    private static int maxSum;

    public int maxPathSum(TreeNode root) {

        maxSum = Integer.MIN_VALUE;

        maxPathSumDownRecur(root);

        return maxSum;
    }

    private int maxPathSumDownRecur(TreeNode root) {

        if (root == null)
            return 0;

        int leftResult = Math.max(0, maxPathSumDownRecur(root.left));
        int rightResult = Math.max(0, maxPathSumDownRecur(root.right));

        maxSum = Math.max(leftResult + rightResult + root.val, maxSum);

        return Math.max(leftResult, rightResult) + root.val;
    }
}
