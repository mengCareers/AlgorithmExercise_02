package CompanyOriented.Google;

import java.util.Stack;

public class PostfixExpToExpressionTree {
    public static void main(String[] args) {
        PostfixExpToExpressionTree inst = new PostfixExpToExpressionTree();
        TreeNode root = inst.toExpressionTree("abc+*d/e+");
        printTree(root);
    }

    private static void printTree(TreeNode root) {

        if (root == null) {
            return;
        }

        printTree(root.left);
        System.out.print((char)root.val + " ");
        printTree(root.right);
    }

    public TreeNode toExpressionTree(String postfixExp) {

        Stack<TreeNode> stack = new Stack<>();
        char[] arrPostfixExp = postfixExp.toCharArray();

        for (char ch : arrPostfixExp) {
            if (ch >= 'a' && ch <= 'z') {
                stack.push(new TreeNode(ch));
            } else { // + = * /
                TreeNode p2 = stack.pop();
                TreeNode p1 = stack.pop();
                TreeNode operatorNode = new TreeNode(ch);
                operatorNode.left = p1;
                operatorNode.right = p2;
                stack.push(operatorNode);
            }
        }

        return stack.peek();
    }
}
