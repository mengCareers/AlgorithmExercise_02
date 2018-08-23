package Test;

import Google.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TestGetSubtreeNodes {

    public static void main(String[] args) {

        TestGetSubtreeNodes inst = new TestGetSubtreeNodes();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        List<TreeNode> nodes = inst.getSubtreeNodes(root);
        nodes.forEach((node) -> {
            System.out.print(node.val + " ");
        });
    }

    public List<TreeNode> getSubtreeNodes(TreeNode root) {

        List<TreeNode> nodes = new ArrayList<>();
        getSubtreeNodesFrom(root, nodes);

        return nodes;
    }

    public void getSubtreeNodesFrom(TreeNode cur, List<TreeNode> nodes) {

        if (cur != null) {
            nodes.add(cur);
        }

        if (cur.left != null) {
            getSubtreeNodesFrom(cur.left, nodes);
        }

        if (cur.right != null) {
            getSubtreeNodesFrom(cur.right, nodes);
        }
    }
}
