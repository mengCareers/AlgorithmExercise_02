package CompanyOriented.Amazon;

import Google.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * input: root
 * output: the most freq subtree sum
 * subtreeSum(node): sum of all node values fromed by the subtree rooted at that node (inclusive)
 */
/*
   5
 /  \
2   -5
subtreeSum(5) = 5 + subtreeSum(2) + subtreeSum(-5)
subtreeSum(2) = 2
subtreeSum(-5) = -5
that is,
subtreeSum(node) = node.val + subtreeSum(node.left) + subtreeSum(node.right)
 */
public class MostFrequentSubtreeSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        MostFrequentSubtreeSum inst = new MostFrequentSubtreeSum();
        inst.findFrequentTreeSum(root);
    }


    public int[] findFrequentTreeSum(TreeNode root) {

        if (root == null) {
            return new int[0];
        }

        Map<Integer, List<Integer>> subtreeSumToRoot = new HashMap<>();
        subtreeSum(root, subtreeSumToRoot);

        int maxSize = 0;
        for (int subtreeSum : subtreeSumToRoot.keySet()) {
            if (subtreeSumToRoot.get(subtreeSum).size() > maxSize) {
                maxSize = subtreeSumToRoot.get(subtreeSum).size();
            }
        }
        List<Integer> roots = new ArrayList<>();
        for (int subtreeSum : subtreeSumToRoot.keySet()) {
            if (maxSize == subtreeSumToRoot.get(subtreeSum).size()) {
                roots.add(subtreeSum);
            }
        }
        int[] result = new int[roots.size()];
        int ri = 0;
        for (int rootVal : roots) {
            result[ri++] = rootVal;
        }

        return result;
    }

    private int subtreeSum(TreeNode node, Map<Integer, List<Integer>> subtreeSumToRoot) {

        if (node == null) {
            return 0;
        }

        int subSum = node.val + subtreeSum(node.left, subtreeSumToRoot) + subtreeSum(node.right, subtreeSumToRoot);

        if (!subtreeSumToRoot.containsKey(subSum))
            subtreeSumToRoot.put(subSum, new ArrayList<>());
        subtreeSumToRoot.get(subSum).add(node.val);

        return subSum;
    }
}
