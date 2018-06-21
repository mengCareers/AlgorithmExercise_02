package Google;

/**
 * input : BST
 * output: kth smallest element
 * BST, inorder traversal and store
 * <p>
 * if modified often
 * # CAN WE USE BINARY SEARCH?
 * but we are not sure which branch to enter
 * # HOW CAN WE?
 * the # of nodes
 */
public class KthSmallestElementInBST {
    public static void main(String[] args) {
        KthSmallestElementInBST inst = new KthSmallestElementInBST();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        inst.kthSmallest(root, 3);
    }

    public int kthSmallest(TreeNode root, int k) {
        int cntLeftNodes = countNodes(root.left);
        if (k <= cntLeftNodes) {
            return kthSmallest(root.left, k);
        }
        if (k == cntLeftNodes + 1) {
            return root.val;
        }
        return kthSmallest(root.right, k - cntLeftNodes - 1);
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
