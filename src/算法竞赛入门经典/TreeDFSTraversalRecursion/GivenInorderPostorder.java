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

postorder: the last one must be the root
[4, 5, 2, 6, 3, 1]
inorder: locate root, [0, ri-1] left sub, [ri+1, len-1] right sub
[4, 2, 5, 1, 6, 3]
for [4, 2, 5] len = 3, first 3 of postorder will be set for it
 */
public class GivenInorderPostorder {

    public static void main(String[] args) {
        GivenInorderPostorder inst = new GivenInorderPostorder();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        inst.buildTree(inorder, postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // corner cases to add

        int n = inorder.length;
        Map<Integer, Integer> valToIdxInorder = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valToIdxInorder.put(inorder[i], i);
        }
        return buildTreeFrom(inorder, 0, n - 1, postorder, 0, n - 1, valToIdxInorder);
    }

    private TreeNode buildTreeFrom(int[] inorder, int istart, int iend, int[] postorder, int pstart, int pend, Map<Integer, Integer> valToIdxInorder) {

        if (pstart > pend || istart > iend) {
            return null;
        }

        int rootVal = postorder[pend];
        TreeNode root = new TreeNode(rootVal);
        int ri = valToIdxInorder.get(rootVal);
//        int ri = istart;
//        for (; ri <= iend; ri++) {
//            if (inorder[ri] == rootVal) {
//                break;
//            }
//        }
        int len = ri - istart;
        root.left = buildTreeFrom(inorder, istart, ri - 1, postorder, pstart, pstart + len - 1, valToIdxInorder);
        root.right = buildTreeFrom(inorder, ri + 1, iend, postorder, pstart + len, pend - 1, valToIdxInorder);

        return root;
    }
}
