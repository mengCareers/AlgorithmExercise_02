package Contest;

import CompanyOriented.Google.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SmallestSubtreeWithAllDeepestNodes {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        if (root == null) {
            return root;
        }
        Set<TreeNode> deepestNodes = getAllDeepestNodes(root);

        return getLCA(root, deepestNodes);
    }

    private Set<TreeNode> getAllDeepestNodes(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Set<TreeNode> levelOfNodes = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            levelOfNodes.clear();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelOfNodes.add(cur);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        return levelOfNodes;
    }

    private TreeNode getLCA(TreeNode root, Set<TreeNode> deepestNodes) {

        if (root == null || deepestNodes.contains(root)) {
            return root;
        }

        TreeNode leftSub = getLCA(root.left, deepestNodes);
        TreeNode rightSub = getLCA(root.right, deepestNodes);
        if (leftSub == null) {
            return rightSub;
        } else if (rightSub == null) {
            return leftSub;
        }

        return root;
    }

}
