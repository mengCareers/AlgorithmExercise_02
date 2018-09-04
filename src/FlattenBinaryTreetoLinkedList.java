
public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        flattenRecur(root);
    }

    private TreeNode flattenRecur(TreeNode root) {

        if (root == null) {
            return root;
        }

        TreeNode leftNode = flattenRecur(root.left); // 2 3 4
        TreeNode rightNode = flattenRecur(root.right); // 5 6
        root.right = leftNode;
        root.left = null;
        TreeNode ptr = root;
        while (ptr.right != null) {
            ptr = ptr.right;
        }
        ptr.right = rightNode;

        return root;
    }
}
