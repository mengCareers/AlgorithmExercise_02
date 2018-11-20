package CompanyOriented.Google.PhoneInterview;

import CompanyOriented.Google.TreeNode;

public class MyBST {

    // Driver Program to test below functions
    public static void main(String[] args) {
        MyBST tree = new MyBST();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
        tree.insertTreeNode(50);
        tree.insertTreeNode(30);
        tree.insertTreeNode(20);
        tree.insertTreeNode(40);
        tree.insertTreeNode(70);
        tree.insertTreeNode(60);
        tree.insertTreeNode(80);

        System.out.println("Inorder traversal of the given tree");
        tree.inorder();

        System.out.println("\nDelete 20");
        tree.deleteTreeNode(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 30");
        tree.deleteTreeNode(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 50");
        tree.deleteTreeNode(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
    }

    TreeNode root;

    public MyBST() {
        root = null;
    }

    public void insertTreeNode(int key) {
        root = insertUtility(root, key);
    }

    private TreeNode insertUtility(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.val) {
            root.left = insertUtility(root.left, key);
        } else if (key > root.val) {
            root.right = insertUtility(root.right, key);
        }

        return root;
    }

    public void deleteTreeNode(int key) {
        root = deleteUtility(root, key);
    }

    private TreeNode deleteUtility(TreeNode root, int key) {
        // find TreeNode with value key
        if (key < root.val) {
            root.left = deleteUtility(root.left, key);
        } else if (key > root.val) {
            root.right = deleteUtility(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // 3.copy value of inorder successor succ
            root.val = getMinimumValue(root.right);
            // delete succ
            root.right = deleteUtility(root.right, root.val);
        }
        return root;
    }

    // inorder successor of curNode is actually the minimum TreeNode of subtree curNode.right
    public int getMinimumValue(TreeNode root) {
        TreeNode ptr = root;
        while (ptr.left != null) {
            ptr = ptr.left;
        }
        return ptr.val;
    }

    public void inorder() {
        inorderUtility(root);
    }

    private void inorderUtility(TreeNode root) {
        if (root != null) {
            inorderUtility(root.left);
            System.out.print(root.val + " ");
            inorderUtility(root.right);
        }
    }
}
