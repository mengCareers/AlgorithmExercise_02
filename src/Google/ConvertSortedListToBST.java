package Google;

import java.util.List;
import java.util.Stack;

/**
 A tree is height balanced only if the depth of the two subtrees of every node are height balanced.
 Base cases:
 • If there are 0 nodes in the List, root is null.
 • If there is 1 node in the List, root is formed by this node.
 • If there are 2 nodes in the tree, root can be either one of them.
 • If there are 3 nodes in the tree, root must be the middle one.
 The rule that reduces all other cases toward the base cases:
 Keep splitting the list into two lists, and set the middleNode as the parent for both sublists. In this way, we can meet the base cases eventually.
 */
public class ConvertSortedListToBST {


    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        if (head.next.next == null) {
            TreeNode tmp = new TreeNode(head.val);
            tmp.right = new TreeNode(head.next.val);
            return tmp;
        }
        ListNode middleNode = splitList(head);
        TreeNode root = new TreeNode(middleNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(middleNode.next);
        return root;
    }

    /**
     * To split the list into two lists equally.
     *
     * @param head
     * @return The new head of the second list
     */
    private ListNode splitList(ListNode head) {
        ListNode fast = head, slow = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        return slow;
    }
}
