package CompanyOriented.Google;

/**
 * length of longest increasing consecutive sequence from parent to child
 * if we stand at node x in level l,
 * we can either go left, or go right
 * my aim will be a node in level l + 1, with value x.val + 1
 * <p>
 * State :
 * we define lenConsecutive as the length of current consecutive sequence
 * <p>
 * Aim State :
 * max(all possible lenConsecutive's)
 * <p>
 * Recursion Equation :
 * Standing at curNode,
 * <p>
 * IF (curNode.val == valExpected), for valExpected = parentNode.val + 1,
 * we can be part of consecutive sequence following the parentNode, i.e., lenConsecutive++;
 * ELSE,
 * we try building a new consecutive sequence, i.e., lenConsecutive = 1;
 * The subtree of curNode also follow the pattern above,
 * and we keep track of the maximum lenConsecutive as maxVal on the fly.
 */
public class BTLongestConsecutiveSequence {
    private int maxVal = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestConsecutiveFrom(root, root.val, 0);
        return maxVal;
    }

    private void longestConsecutiveFrom(TreeNode curNode, int valExpected, int lenConsecutive) {
        if (curNode == null) {
            return;
        }
        if (curNode.val == valExpected) {
            lenConsecutive++;
        } else {
            lenConsecutive = 1;
        }
        maxVal = Math.max(maxVal, lenConsecutive);
        longestConsecutiveFrom(curNode.left, curNode.val + 1, lenConsecutive);
        longestConsecutiveFrom(curNode.right, curNode.val + 1, lenConsecutive);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
