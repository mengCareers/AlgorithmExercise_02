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
        buildParentPointers(childToParent, root);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);
        int dist = 0;
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode curNode = queue.poll();
                if (dist == K) {
                    result.add(curNode.val);
                }
                if (curNode.left != null && !visited.contains(curNode.left)) {
                    queue.add(curNode.left);
                    visited.add(curNode.left);
                }
                if (curNode.right != null && !visited.contains(curNode.right)) {
                    queue.add(curNode.right);
                    visited.add(curNode.right);
                }
                if (childToParent.containsKey(curNode) && !visited.contains(childToParent.get(curNode))) {
                    queue.add(childToParent.get(curNode));
                    visited.add(childToParent.get(curNode));
                }
            }
            if (!result.isEmpty()) {
                return result;
            }
            dist++;
        }

        return result;
    }

    private void buildParentPointers(Map<TreeNode, TreeNode> childToParent, TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.left != null) {
                queue.add(curNode.left);
                childToParent.put(curNode.left, curNode);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                childToParent.put(curNode.right, curNode);
            }
        }
    }
}