package Google;

/**
 * input: preorder[], inorder[] without duplicates
 * output: root (of constructed tree)
 * <p>
 * preorder -> root
 * root in inorder splits the array into 2 parts, the left subtree and right subtree
 * how do we find the root of it
 * 3
 * / \
 * 9  20
 * /  /  \
 * 4 15   7
 * /
 * 10
 * preorder: 3,9,4,20,15,10,7
 * inorder: 4,9,3,10,15,20,7
 We can figure out the root with the help of preorder[]. And the root's value will split inorder[] into two parts, i.e., inorder[]for its left subtree and inorder[]for its right subtree. According to each subtree's inorder[], we can find the corresponding preorder[]. In this way, the problem becomes smaller and smaller.
 We can figure out the root with the help of preorder[]. And the root's value will split inorder[] into two parts, i.e., inorder[]for its left subtree and inorder[]for its right subtree. According to each subtree's inorder[], we can find the corresponding preorder[]. In this way, the problem becomes smaller and smaller.
 */
public class ConstructBTFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeFrom(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeFrom(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe || is > ie) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[ps]);
        int i = is;
        for (; i <= ie; i++) {
            if (inorder[i] == root.val) {
                break;
            }
        }
        root.left = buildTreeFrom(preorder, ps + 1, ps + i - is, inorder, is, i - 1);
        root.right = buildTreeFrom(preorder, ps + i - is + 1, pe, inorder, i + 1, ie);
        return root;
    }
}
