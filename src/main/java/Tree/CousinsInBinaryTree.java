package Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CousinsInBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, Integer> nodeToParent = new HashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean meetX = false, meetY = false;

        while (!queue.isEmpty()) {
            int sz = queue.size();

            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (node.val == x) meetX = true;
                else if (node.val == y) meetY = true;
                if (node.left != null) {
                    queue.offer(node.left);
                    nodeToParent.put(node.left.val, node.val);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nodeToParent.put(node.right.val, node.val);
                }
            }
            if (meetX == !meetY) return false;
        }
        return nodeToParent.get(x) != nodeToParent.get(y);
    }

    public static void main(String[] args) {
        CousinsInBinaryTree inst = new CousinsInBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(inst.isCousins(root, 5, 4));

    }
}
/*
 * root depth 0
 * cousin if same depth but diff parents
 * uniq values,
 * input: root, x, y
 * true if x and y are cousins
 *
 * how to detect cousins?
 * BFS traversal
 *  each node's depth -- bfs
 *  each node's parent --
 *  if same depth && diff parent
 *

 * */
