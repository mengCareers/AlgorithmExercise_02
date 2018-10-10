package 算法竞赛入门经典.TreeDFSTraversalRecursion;

import CompanyOriented.Google.TreeNode;

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

preorder: the first one must be the root
[1, 2, 4, 5, 3, 6]
2nd one is left subtree root,
locate in post order, lri
[0, lri] is post order sequence for left subtree, get len
[2nd, len - 1] is preorder sequence for left subtree, get len
 */
public class GivenPreorderPostorder {
    int preStart;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        // corner cases to add

        int n = pre.length, pi = 0, preStart = 0;
        Map<Integer, Integer> valToIndexPost = new HashMap<>();
        for (int val : post) {
            valToIndexPost.put(val, pi++);
        }

        return constructFromPrePostFrom(pre, post, 0, n - 1, valToIndexPost);
    }

    private TreeNode constructFromPrePostFrom(int[] pre, int[] post, int postStart, int postEnd, Map<Integer, Integer> valToIndexPost) {

        if (postStart > postEnd) {
            return null;
        }

        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);

        if (preStart == pre.length - 1 || postStart == postEnd) {
            return root;
        }
        preStart++;
        int leftVal = pre[preStart];
        int lri = valToIndexPost.get(leftVal);
        root.left = constructFromPrePostFrom(pre, post, postStart, lri, valToIndexPost);
        root.right = constructFromPrePostFrom(pre, post, lri + 1, postEnd - 1, valToIndexPost);

        return root;
    }
}
