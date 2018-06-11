package Google;

import java.util.*;

/**
 It is inductive to apply the modified Level Order Traversal.
 We use boolean leftToRight to indicate the order of the current level.
 Obviously, leftToRight = !leftToRight; before we enter the next level.
 As for the order from right to left, we utilize Stack<TreeNode> tmpStack to 'reverse' the order of polled TreeNodes.
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(BTLongestConsecutiveSequence.TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        boolean leftToRight = true;
        List<List<Integer>> result = new ArrayList<>();
        Queue<BTLongestConsecutiveSequence.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Stack<BTLongestConsecutiveSequence.TreeNode> tmpStack = new Stack<>();
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                BTLongestConsecutiveSequence.TreeNode tmp = queue.poll();
                if (leftToRight) {
                    list.add(tmp.val);
                } else {
                    tmpStack.push(tmp);
                }
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            while (!tmpStack.isEmpty()) {
                list.add(tmpStack.pop().val);
            }
            result.add(list);
            leftToRight = !leftToRight;
        }
        return result;
    }
}
