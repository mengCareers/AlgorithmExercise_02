package Contest;

import CompanyOriented.Google.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
output: true if leaf value sequence is the same
how do we get leaf value sequence?
DFS, and add to list when it is the last row
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        List<Integer> leafSeq1 = getLeafSequence(root1);
        List<Integer> leafSeq2 = getLeafSequence(root2);

        return leafSeq1.equals(leafSeq2);
    }

    private List<Integer> getLeafSequence(TreeNode root) {

        List<Integer> leafSequence = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            if (curNode.left == null && curNode.right == null) {
                leafSequence.add(curNode.val);
            } else {
                if (curNode.left != null) {
                    stack.push(curNode.left);
                }
                if (curNode.right != null) {
                    stack.push(curNode.right);
                }
            }
        }

        return leafSequence;
    }
}
