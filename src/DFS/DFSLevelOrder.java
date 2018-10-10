package DFS;

import CompanyOriented.Google.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dfs build a map
 * dfs:
 * base case: root == null, return
 */
public class DFSLevelOrder {

    Map<Integer, List<Integer>> depthToNodes;
    int maxDepth;

    public List<List<Integer>> DFSLevelOrder(TreeNode root) {

        maxDepth = 0;
        depthToNodes = new HashMap<>();
        findLeavesFrom(root, 1);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = maxDepth; i >= 1; i--) {
            result.add(depthToNodes.get(i));
        }

        return result;
    }

    private void findLeavesFrom(TreeNode root, int depth) {

        if (root == null) {
            return;
        }

        if (!depthToNodes.containsKey(depth)) {
            depthToNodes.put(depth, new ArrayList<>());
        }
        depthToNodes.get(depth).add(root.val);
        maxDepth = Math.max(maxDepth, depth);

        findLeavesFrom(root.left, depth + 1);
        findLeavesFrom(root.right, depth + 1);
    }

}
