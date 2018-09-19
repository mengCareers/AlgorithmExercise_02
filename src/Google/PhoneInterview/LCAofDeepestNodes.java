package Google.PhoneInterview;

import Google.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class LCAofDeepestNodes {

    public static void main(String[] args) {
        LCAofDeepestNodes inst = new LCAofDeepestNodes();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.right = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        inst.printTree(root);
        TreeNode newRoot = inst.subtreeWithAllDeepest(root);
        inst.printTree(newRoot);
    }

    private void printTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + ", ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    class Result {

        TreeNode node;
        int dist;

        public Result(TreeNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    /*
        0
       /
     1
    /
    3
     */
    private Result dfs(TreeNode root) {

        if (root == null) {
            return new Result(null, 0);
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);
        if (left.dist < right.dist) {
            return new Result(right.node, right.dist + 1);
        }
        if (left.dist > right.dist) {
            return new Result(left.node, left.dist + 1);
        }

        return new Result(root, right.dist + 1);
    }
}
