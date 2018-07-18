package Amazon;

import Google.TreeNode;

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

        if (root == null) {
            return result;
        }

        if (root.val <= V) {
            result[0] = root;
            TreeNode[] rightRes = splitBST(root.right, V);
            root.right = rightRes[0];
            result[1] = rightRes[1];
        } else {
            result[1] = root;
            TreeNode[] leftRes = splitBST(root.left, V);
            root.left = leftRes[1];
            result[0] = leftRes[0];
        }

        return result;
    }

    public TreeNode[] splitBSTIterative(TreeNode root, int V) {
        TreeNode[] result = new TreeNode[2];
        TreeNode nodeToSplit = findNodeToSplit(root, V);
        Map<TreeNode, TreeNode> childToParent = getNodeParents(root);
        result[0] = nodeToSplit;
        childToParent.get(nodeToSplit).left = nodeToSplit.right;
        nodeToSplit.right = null;
        result[1] = root;
        return result;
    }

    private Map<TreeNode, TreeNode> getNodeParents(TreeNode root) {
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.left != null) {
                childToParent.put(curNode.left, curNode);
            }
            if (curNode.right != null) {
                childToParent.put(curNode.right, curNode);
            }
        }
        return childToParent;
    }

    private TreeNode findNodeToSplit(TreeNode root, int v) {
        if (root == null) {
            return root;
        }
        if (root.val < v) {
            return findNodeToSplit(root.right, v);
        } else if (root.val > v) {
            return findNodeToSplit(root.left, v);
        } else {
            return root;
        }
    }
}
