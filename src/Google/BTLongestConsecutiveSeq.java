package Google;

/**
 * parent-child connection, pre-order
 * pre-order, and find LIS
 * How about DFS without backtracking
 */
public class BTLongestConsecutiveSeq {
    int maxVal;

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestConsecutiveFrom(root, root.val, 0);
        return maxVal;
    }

    private void longestConsecutiveFrom(TreeNode root, int toCompare, int curLen) {
        if (root == null) {
            return;
        }
        if (root.val == toCompare) {
            curLen++;
        } else {
            curLen = 1;
        }
        maxVal = Math.max(maxVal, curLen);
        longestConsecutiveFrom(root.left, root.val + 1, curLen);
        longestConsecutiveFrom(root.right, root.val + 1, curLen);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
