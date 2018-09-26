package CompanyOriented.Amazon;

import Google.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * input: root
 * output: true if possible to partition by removing one edge that
 * two trees with equal sum
 */
/*
Logical Thinking
It is inductive that we get the totalSum of the tree first, then totalSum / 2 should be the target sum for both tree partitions.
The tree partition mentioned above is actually a subtree. So we keep track of all subtree sums while getTotalSum().
Please note that, totalSum / 2 cannot be the sum of the whole tree, for example,
   0
  / \
-1   1
the tree above will return false. Thus, we remove the last element of the list subtreeSum.e equal to targetSum
record the sum of every subtree.
 */
public class EqualTreePartition {

    public static void main(String[] args) {
        EqualTreePartition inst = new EqualTreePartition();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(10);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        inst.checkEqualTree(root);
    }

    List<Integer> subtreeSum;

    public boolean checkEqualTree(TreeNode root) {
        subtreeSum = new LinkedList<>();
        int totalSum = getTotalSum(root);
        if (totalSum % 2 != 0) {
            return false;
        }
        subtreeSum.remove(subtreeSum.size() - 1);
        return subtreeSum.contains(totalSum / 2);
    }

    private int getTotalSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int totalSum = root.val + getTotalSum(root.left) + getTotalSum(root.right);
        subtreeSum.add(totalSum);
        return totalSum;
    }
}
