package Tree;

import CompanyOriented.Google.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * max tree: node greater than subtree nodes
 * */
public class MaxBT {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) return null;

        // Restore A to list - inorder.
        List<Integer> nodeVals = new ArrayList<>();
        preorder(root, nodeVals);

        // List append val.
        nodeVals.add(val);

        // Build tree.
        return buildTree(nodeVals, 0, nodeVals.size() - 1);
    }

    private TreeNode buildTree(List<Integer> nodeVals, int start, int end) {
        if (start == end) return new TreeNode(nodeVals.get(start));
        int maxIndex = findMaxIndex(nodeVals, start, end);
        TreeNode root = new TreeNode(nodeVals.get(maxIndex));
        TreeNode leftRoot = buildTree(nodeVals, start, maxIndex - 1);
        TreeNode rightRoot = buildTree(nodeVals, maxIndex + 1, end);
        root.left = leftRoot;
        root.right = rightRoot;
        return root;
    }

    private int findMaxIndex(List<Integer> nodeVals, int start, int end) {
        int maxVal = Integer.MIN_VALUE, maxIndex = start;
        for (int i = start; i <= end; i++) {
            if (maxVal < nodeVals.get(i)) {
                maxVal = nodeVals.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void preorder(TreeNode root, List<Integer> nodeVals) {
        if (root == null) return;
        preorder(root.left, nodeVals);
        nodeVals.add(root.val);
        preorder(root.right, nodeVals);
    }
}
