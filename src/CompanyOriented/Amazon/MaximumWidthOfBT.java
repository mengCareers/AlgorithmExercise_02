package CompanyOriented.Amazon;

import Google.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * width of tree: is the maximum width among all levels
 * bt is full bt
 * width of level: is the length between non-null end nodes (null between counted)
 */
/*
level order traversal
keep track of width of each level
get the maximum one among
 */

/**
 * MENTOR: keep track of that node's position
 */
public class MaximumWidthOfBT {
    public static void main(String[] args) {
        MaximumWidthOfBT inst = new MaximumWidthOfBT();
        TreeNode root = new TreeNode(1);
        inst.widthOfBinaryTree(root);
    }

    class TreeNodeWithPosition {
        TreeNode node;
        int position;
        int depth;

        public TreeNodeWithPosition(TreeNode node, int position, int depth) {
            this.node = node;
            this.position = position;
            this.depth = depth;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNodeWithPosition> queue = new LinkedList<>();
        queue.offer(new TreeNodeWithPosition(root, 0, 0));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            int leftmost = Integer.MAX_VALUE, rightmost = Integer.MIN_VALUE;
            for (int i = 0; i < sz; i++) {
                TreeNodeWithPosition curNode = queue.poll();
                if (curNode.node != null) {
                    leftmost = Math.min(leftmost, curNode.position);
                    rightmost = Math.max(rightmost, curNode.position);
                    queue.offer(new TreeNodeWithPosition(curNode.node.left, 2 * curNode.position, curNode.depth + 1));
                    queue.offer(new TreeNodeWithPosition(curNode.node.right, 2 * curNode.position + 1, curNode.depth + 1));
                }
            }
            if (rightmost != Integer.MIN_VALUE && leftmost != Integer.MAX_VALUE) {
                int width = rightmost - leftmost + 1;
                maxWidth = Math.max(maxWidth, width);
            }
        }

        return maxWidth;
    }
}
