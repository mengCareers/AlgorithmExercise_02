package CompanyOriented.Google;

import java.util.HashMap;
import java.util.Map;

/**
 * Find all duplicate subtrees.
 * a node is also considered as a subree
 * <p>
 * # Hashing and Inorder Traversal
 */
public class FindTheNumberOfIdenticalSubtree {
    public static void main(String args[]) {
        FindTheNumberOfIdenticalSubtree inst = new FindTheNumberOfIdenticalSubtree();
        Node root = null;
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(2);
        root.right.left.left = new Node(4);
        root.right.right = new Node(4);
        int answer = inst.countAllDups(root);
        System.out.println("Total:" + answer);
    }

    Map<String, Integer> map;
    int countDups = 0;

    public int countAllDups(Node root) {
        map = new HashMap<>();
        inorder(root);
        return countDups;
    }

    private String inorder(Node node) {
        if (node == null) {
            return "";
        }
        String inorderStr = "(" +
                inorder(node.left) +
                node.data +
                inorder(node.right) + ")";
        System.out.println(inorderStr);
        /**
         Input :
           1
          / \
         2   3
        /   / \
       4   2   4
          /
         4
         (4)
         ((4)2)
         (4)
         ((4)2)
         (4)
         (((4)2)3(4))
         (((4)2)1(((4)2)3(4)))
         */
        if (map.containsKey(inorderStr) && map.get(inorderStr) == 1) {
            countDups++;
        }
        map.put(inorderStr, map.getOrDefault(inorderStr, 0) + 1);
        return inorderStr;
    }
}

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}