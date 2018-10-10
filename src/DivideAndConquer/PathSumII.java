package DivideAndConquer;

import CompanyOriented.Google.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
current node and sum can define state
state(root, sum) true if there is a valid path from root to reach sum
state(root, sum) is goal
state(root, sum) = state(root.left, sum - root.val) || state(root.right, sum - root.val)
state(null, 0) is the base case
 */
public class PathSumII {

    public static void main(String[] args) {
        PathSumII inst = new PathSumII();
        TreeNode root = new TreeNode(5);
        int sum = 9;
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(1);
        System.out.println(inst.pathSum(root, sum));
    }

    List<List<Integer>> result;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<>();
        if (root == null)
            return result;
        hasPathSumFrom(root, sum, new ArrayList<>());
        return result;
    }

    private void hasPathSumFrom(TreeNode root, int sum, List<Integer> path) {

        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        if (root.left != null) {
            hasPathSumFrom(root.left, sum - root.val, path);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            hasPathSumFrom(root.right, sum - root.val, path);
            path.remove(path.size() - 1);
        }

    }
}
