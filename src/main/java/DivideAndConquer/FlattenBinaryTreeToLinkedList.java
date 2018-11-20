package DivideAndConquer;

import CompanyOriented.Google.TreeNode;

public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList inst = new FlattenBinaryTreeToLinkedList();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        inst.flatten(root);
    }

    TreeNode ptr;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        ptr = new TreeNode(0);
        flattenUtil(root);
        ptr.right = null;
    }


    private void flattenUtil(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode originalRightNode = node.right;
        TreeNode originalLeftNode = node.left;
        ptr.right = node;
        ptr.left = null;
        ptr = node;
        flattenUtil(originalLeftNode);
        flattenUtil(originalRightNode);
    }
}
