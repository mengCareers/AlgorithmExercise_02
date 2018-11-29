package CompanyOriented.Google.Interview;

import CompanyOriented.Google.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a BT, delete a node from it by making sure that tree shrinks from the bottom
(i.e. the deleted node is replaced by the bottom most and rightmost node)
Logical Thought:
find bottom most and rightmost node N, assign N's value to the node to delete, and remove N

Traversal, IN PRE POST BFS
BFS find nodeToDelete, and last node
value assign, and delete last node's parent.ri

how do we detect the bottom most and rightmost node? .left = null, .right = bull
if you decide BFS, just write the structure of BFS
 */
/*
    0
   / \
 1    2
/\   /\
3 4 5  6
 */
public class DeletionInBT {
    public static void main(String[] args) {
        DeletionInBT inst = new DeletionInBT();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        inst.printTree(root);
        inst.shrinkTree(root, 3);
        inst.printTree(root);
    }

    private void printTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + ", ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    public TreeNode shrinkTree(TreeNode root, int valToDelete) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode lastNodeParent = null, nodeToDelete = null;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (valToDelete == node.val) {
                nodeToDelete = node;
            }
            if (node.left != null) {
                queue.offer(node.left);
                lastNodeParent = node;
            }
            if (node.right != null) {
                queue.offer(node.right);
                lastNodeParent = node;
            }
        }

        if (lastNodeParent.right != null) {
            nodeToDelete.val = lastNodeParent.right.val;
            lastNodeParent.right = null;
        } else {
            nodeToDelete.val = lastNodeParent.left.val;
            lastNodeParent.left = null;
        }

        return root;
    }
}
