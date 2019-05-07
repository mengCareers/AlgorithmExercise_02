package Tree;

import CompanyOriented.Google.TreeNode;

import java.util.Arrays;

public class ConstructBSTPreorder {
    public static void main(String[] args) {
        int[] preorder = {19, 4, 8, 11};
        // 19,4,8,11 pre
        // 4 8 11 19 in
        // 19 is root

        ConstructBSTPreorder inst = new ConstructBSTPreorder();
        TreeNode root = inst.bstFromPreorder(preorder);
        System.out.println(root.val);
//        System.out.println(root.left.val);
//        System.out.println(root.right.val);
//        System.out.println(root.left.left.val);
//        System.out.println(root.left.right.val);
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        int n = preorder.length;
        if (n == 1) return new TreeNode(preorder[0]);
        int[] inorder = Arrays.copyOf(preorder, n);
        Arrays.sort(inorder);
        // Given inorder and preorder, construct tree.
        return constructTree(inorder, 0, n - 1, preorder, 0, n - 1);
    }

    private TreeNode constructTree(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd) {
        if (inStart == inEnd) return new TreeNode(inorder[inStart]);
        if (inStart > inEnd) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int indexRoot = findRootIndexInInorder(inorder, rootVal);
        int leftLen = indexRoot - inStart;
        // Left subtree [inStart, indexRoot - 1], right subtree [indexRoot + 1, inEnd] in inorder.
        // Left subtree [preStart + 1, preStart + leftLen], right subtree [preStart + leftLen + 1, preEnd] in preorder.
        root.left = constructTree(inorder, inStart, indexRoot - 1, preorder, preStart + 1, preStart + leftLen);
        root.right = constructTree(inorder, indexRoot + 1, inEnd, preorder, preStart + leftLen + 1, preEnd);
        return root;
    }

    private int findRootIndexInInorder(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (val == inorder[i]) return i;
        }
        throw null;
    }
}
