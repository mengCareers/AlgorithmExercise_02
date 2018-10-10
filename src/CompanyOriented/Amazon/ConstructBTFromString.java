package CompanyOriented.Amazon;

import CompanyOriented.Google.TreeNode;

import java.util.Stack;

/**
 * input: s as representation of BT contains (, ), int
 */
/*
4 2
 /\
3 1
 */
public class ConstructBTFromString {
    public TreeNode str2tree(String s) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode parent = null, curNode = null;
        int sign = 1, si = 0;

        while (si < s.length()) {
            if (s.charAt(si) == ')') {
                curNode = stack.pop();
                parent = stack.peek();
                if (parent.left != null) {
                    parent.right = curNode;
                } else {
                    parent.left = curNode;
                }
                si++;
            } else if (s.charAt(si) == '-') {
                sign = -1;
                si++;
            } else if (s.charAt(si) == '(') {
                si++;
            } else {
                int num = 0;
                while (si < s.length() && s.charAt(si) >= '0' && s.charAt(si) <= '9') {
                    num = num * 10 + s.charAt(si) - '0';
                    si++;
                }
                num *= sign;
                sign = 1;
                stack.push(new TreeNode(num));
            }
        }

        if (!stack.isEmpty()) {
            return stack.peek();
        }

        return parent;
    }
}