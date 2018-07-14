package Amazon;

import Google.TreeNode;

import java.util.*;

/**
 * INPUT: root, target, k
 * OUTPUT: list of nodes dist k from target
 * distanceK(target) = distanceK(target.left) +/- 1
 * = distanceK(target.right) +/- 1
 * = distanceK(target.right) - 1
 */
public class AllNodesDistanceKinBT {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        annotateParentFrom(childToParent, root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        int distance = 0;
        List<Integer> result = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                visited.add(curNode);
                if (distance == K) {
                    result.add(curNode.val);
                }
                if (curNode.left != null && !visited.contains(curNode.left)) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null && !visited.contains(curNode.right)) {
                    queue.offer(curNode.right);
                }
                if (childToParent.containsKey(curNode) && !visited.contains(childToParent.get(curNode))) {
                    queue.offer(childToParent.get(curNode));
                }
            }
            if (!result.isEmpty()) {
                return result;
            }
            distance++;
        }

        return result;
    }

    private void annotateParentFrom(Map<TreeNode, TreeNode> childToParent, TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode curNode = stack.pop();
            if (curNode.left != null) {
                stack.push(curNode.left);
                childToParent.put(curNode.left, curNode);
            }
            if (curNode.right != null) {
                stack.push(curNode.right);
                childToParent.put(curNode.right, curNode);
            }
        }
    }
}
