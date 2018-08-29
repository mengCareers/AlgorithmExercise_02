package Contest;

import Google.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
construct all possible trees, check if full bt, add root to
any node can be root
0 null
[1] 1

[123]  6, all possible trees

[12345]

 */
public class AllPossibleFullBT {

    public List<TreeNode> allPossibleFBT(int N) {
        if (N % 2 == 0) {
            return new ArrayList<>();
        }
        return generateTrees(N);
    }

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
                    TreeNode root = new TreeNode(0);
                    if (leftNode != null && rightNode != null) {
                        root.left = leftNode;
                        root.right = rightNode;
                        result.add(root);
                    }
                }
            }
        }

        return result;
    }
}

