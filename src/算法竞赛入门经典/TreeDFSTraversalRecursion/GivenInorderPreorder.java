package 算法竞赛入门经典.TreeDFSTraversalRecursion;

import Google.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
     1
   /  \
  2    3
 /\   /
4  5 6

preorder: the first one must be the root
[1, 2, 4, 5, 3, 6]
inorder: locate root, [0, ri-1] left sub, [ri+1, len-1] right sub
[4, 2, 5, 1, 6, 3]
for [4, 2, 5] len = 3, first 3 of preorder after root will be set for it
 */
public class GivenInorderPreorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // corner cases to add

        Map<Integer, Integer> valToIndex = new HashMap<>();
        int vi = 0, n = inorder.length;
        for (int val : inorder)
            valToIndex.put(val, vi++);

        return buildTreeFrom(preorder, 0, n - 1, inorder, 0, n - 1, valToIndex);
    }

    private TreeNode buildTreeFrom(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend, Map<Integer, Integer> valToIndex) {

        if (pstart > pend || istart > iend) {
            return null;
        }

        int rootVal = preorder[pstart];
        TreeNode root = new TreeNode(rootVal);

        if (istart == iend) {
            return root;
        }

        int ri = valToIndex.get(rootVal);
        int len = ri - istart;
        root.left = buildTreeFrom(preorder, pstart + 1, pstart + len, inorder, istart, ri - 1, valToIndex);
        root.right = buildTreeFrom(preorder, pstart + len + 1, pend, inorder, ri + 1, iend, valToIndex);

        return root;
    }
}
