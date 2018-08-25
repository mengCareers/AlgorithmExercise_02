package DynamicProgramming;

import Google.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBSTII {
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        return generateTreesFrom(1, n);
    }

    private List<TreeNode> generateTreesFrom(int s, int e) {

        List<TreeNode> result = new ArrayList<>();

        if (s > e) {
            result.add(null);
            return result;
        }

        List<TreeNode> leftSub, rightSub;

        for (int ri = s; ri <= e; ri++) {
            leftSub = generateTreesFrom(s, ri - 1);
            rightSub = generateTreesFrom(ri + 1, e);
            for (TreeNode leftNode : leftSub) {
                for (TreeNode rightNode : rightSub) {
                    TreeNode root = new TreeNode(ri);
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }

        return result;
    }
}
