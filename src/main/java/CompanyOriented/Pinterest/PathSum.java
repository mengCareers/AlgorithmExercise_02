package CompanyOriented.Pinterest;

import CompanyOriented.Google.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).


 */
/*
if pass root,
# of path = pathSum(root.left, sum - root.val) + pathSum(root.right, sum - root.val)
ir not pass root

 */
public class PathSum {
    private Map<Integer, Integer> map; // key: prefixSum, value: number of path sum to prefixSum.
    private int result = 0;

    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        map = new HashMap<>();
        map.put(0, 1);
        preorder(root, 0, sum);
        return result;
    }

    // preorder
    private void preorder(TreeNode node, int prefixSum, int target) {
        if (node == null)
            return;
        prefixSum += node.val;
        result += map.getOrDefault(prefixSum - target, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        preorder(node.left, prefixSum, target);
        preorder(node.right, prefixSum, target);

        map.put(prefixSum, map.get(prefixSum) - 1);

    }
}
