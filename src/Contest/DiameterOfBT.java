package Contest;

import Google.TreeNode;

public class DiameterOfBT {

    public int diameterOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int rootDiameter = getDepth(root.left) + getDepth(root.right);
        int lDiameter = diameterOfBinaryTree(root.left);
        int rDiameter = diameterOfBinaryTree(root.right);

        return Math.max(rootDiameter, Math.max(lDiameter, rDiameter));
    }

    private int getDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
