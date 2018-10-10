package Contest;

import CompanyOriented.Google.TreeNode;

/*
538. Convert BST to Greater Tree
 */
public class ConvertBSTToGreaterTree {
    int cummuSum = 0;

    public TreeNode convertBST(TreeNode root) {

        if (root == null) {
            return null;
        }
        convertUtil(root);

        return root;
    }

    private void convertUtil(TreeNode cur) {

        if (cur == null) {
            return;
        }

        convertUtil(cur.right);
        cur.val += cummuSum;
        cummuSum = cur.val;
        convertUtil(cur.left);
    }
}
