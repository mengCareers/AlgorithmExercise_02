package CompanyOriented.Google;

/**
 * state : dp[i] as # of nodes along the longest path from node i node down to the farthest leaf
 * final state : dp[root]
 * state transfer : dp[i] = max(dp[i.left], dp[i.right]) + 1
 * <p>
 * DFS
 * start point : leaf
 * for each i {
 * dp[i] = max(dp[i.left], dp[i.right]) + 1
 * }
 * end point : root
 */
public class MaximumDepthofBT {
    public int maxDepth(TreeNode root) {
        return maxDepthFrom(root);
    }

    private int maxDepthFrom(TreeNode i) {
        if (i == null) {
            return 0;
        }
        int lDepth = maxDepthFrom(i.left);
        int rDepth = maxDepthFrom(i.right);
        return Math.max(lDepth, rDepth) + 1;
    }
}
