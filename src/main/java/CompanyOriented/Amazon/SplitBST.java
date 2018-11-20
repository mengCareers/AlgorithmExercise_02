package CompanyOriented.Amazon;

import CompanyOriented.Google.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * split BST into 2 such that 1st <= V, 2nd > V
 */
/*
find the node <= V, then it should be split (2 - 4), its right replace it
 */
public class SplitBST {
    public TreeNode[] splitBST(TreeNode root, int V) {

        TreeNode[] result = new TreeNode[2];

        if (root == null)
            return result;

        if (root.val > V) {
            result[1] = root;
            TreeNode[] splitLeft = splitBST(root.left, V);
            root.left = splitLeft[1];
            result[0] = splitLeft[0];
        } else {
            result[0] = root;
            TreeNode[] splitRight = splitBST(root.right, V);
            root.right = splitRight[0];
            result[1] = splitRight[1];
        }

        return result;
    }

}
