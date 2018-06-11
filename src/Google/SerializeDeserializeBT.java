package Google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * tree traversal x 2
 * or
 * level order traversal + "NULL"
 *
 * We can serialize BT using Level Order Traversal. For example,
 *
 *  1
 *   \
 *    3
 *   / \
 *  4   5
 *
 * We serialize the tree above as "1 N 3 4 5 N N N N".
 * When we desiralize the BT, if (!nodes[ni].equals("N")), we have to check whether the corresponding TreeNode has children.
 */
public class SerializeDeserializeBT {
    public String serialize(BTLongestConsecutiveSequence.TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<BTLongestConsecutiveSequence.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            BTLongestConsecutiveSequence.TreeNode tmp = queue.poll();
            if (tmp == null) {
                result.append("N").append(" ");
            } else {
                result.append(tmp.val).append(" ");
                queue.add(tmp.left);
                queue.add(tmp.right);
            }
        }
        return result.toString().trim();
    }

    public BTLongestConsecutiveSequence.TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(" ");
        BTLongestConsecutiveSequence.TreeNode root = new BTLongestConsecutiveSequence.TreeNode(Integer.valueOf(nodes[0]));
        Queue<BTLongestConsecutiveSequence.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ni = 1;
        while (!queue.isEmpty()) {
            BTLongestConsecutiveSequence.TreeNode tmp = queue.poll();
            if (!nodes[ni].equals("N")) {
                tmp.left = new BTLongestConsecutiveSequence.TreeNode(Integer.valueOf(nodes[ni]));
                queue.add(tmp.left);
            }
            ni++;
            if (!nodes[ni].equals("N")) {
                tmp.right = new BTLongestConsecutiveSequence.TreeNode(Integer.valueOf(nodes[ni]));
                queue.add(tmp.right);
            }
            ni++;
        }
        return root;
    }

    public static void main(String[] args) {
        SerializeDeserializeBT inst = new SerializeDeserializeBT();
        BTLongestConsecutiveSequence.TreeNode root = new BTLongestConsecutiveSequence.TreeNode(1);
        root.left = null;
        root.right = new BTLongestConsecutiveSequence.TreeNode(3);
//        root.left.left = null;
//        root.left.right = null;
        root.right.left = new BTLongestConsecutiveSequence.TreeNode(4);
        root.right.right = new BTLongestConsecutiveSequence.TreeNode(5);
        String ans = inst.serialize(root);
        System.out.println(ans);
        BTLongestConsecutiveSequence.TreeNode root2 = inst.deserialize(ans);
        String ans2 = inst.serialize(root2);
        System.out.println(ans2);
    }
}


