package CompanyOriented.Pinterest;

import CompanyOriented.Google.TreeNode;

public class LongestIncreasingSequenceInBT {

    /*
    If asked longest increasing sequence's length
    state:
        length of increasing ending at node
    goal:
        max(length of increasing ending at node)
    transition:
        if (node > node.parent)
            length of increasing ending at node++;
        else
            length of increasing ending at node = 1
        BUT WE DON'T HAVE node.parent
        SO length of increasing ending at node.left / node.right
        XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        if (node >= node.left && node >= node.right)
            length of increasing ending = 1
        if (node < node.left)
            length of increasing ending++;
            further check node.left
        if (node < node.right)
            length of increasing ending++;
            further check node.right
        XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        we stuck us into a mess
        SO WE TRY NOT USING TAIL RECURSION,

     */
    private static int lenLongestInc;

    public int longestIncreasing(TreeNode root) {
        if (root == null) {
            return 0;
        }
        lenLongestInc = 0;
        longestIncreasingRecur(root);
        return lenLongestInc;
    }

    private int longestIncreasingRecur(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int len = 1;
        if (node.left != null) {
            int leftLen = longestIncreasingRecur(node.left);
            if (node.val < node.left.val) {
                len = leftLen + 1;
            }
        }
        if (node.right != null) {
            int rightLen = longestIncreasingRecur(node.right);
            if (node.val < node.right.val) {
                len = Math.max(len, rightLen + 1);
            }
        }
        lenLongestInc = Math.max(lenLongestInc, len);
        return len;
    }

    /*
    If asked longest increasing consecutive sequence's length;
     */

    private static int lenLongest;

    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        lenLongest = 0;
        longestConsecutive(root, root.val, 0);

        return lenLongest;
    }

    private void longestConsecutive(TreeNode node, int targetVal, int lenConsecutive) {

        if (node == null) {
            return;
        }

        if (node.val == targetVal) {
            lenConsecutive++;
        } else {
            lenConsecutive = 1;
        }

        lenLongest = Math.max(lenConsecutive, lenLongest);
        longestConsecutive(node.left, node.val + 1, lenConsecutive);
        longestConsecutive(node.right, node.val + 1, lenConsecutive);
    }
}
