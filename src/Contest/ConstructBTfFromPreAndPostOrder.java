package Contest;

import CompanyOriented.Google.TreeNode;

public class ConstructBTfFromPreAndPostOrder {
    int preStart;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        preStart = 0;
        return constructFromPrePostUtil(pre, post, 0, post.length - 1);
    }

    private TreeNode constructFromPrePostUtil(int[] pre, int[] post, int postStart, int postEnd) {

        if (postStart > postEnd || preStart >= pre.length) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);
        preStart++;

        if (postStart == postEnd) {
            return root;
        }

        int pi = postStart;
        for (; pi <= postEnd; pi++) {
            if (post[pi] == pre[preStart]) {
                break;
            }
        }

        root.left = constructFromPrePostUtil(pre, post, postStart, pi);
        root.right = constructFromPrePostUtil(pre, post, pi + 1, postEnd - 1);

        return root;
    }
}
