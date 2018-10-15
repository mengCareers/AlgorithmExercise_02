package CompanyOriented.Pinterest;

import CompanyOriented.Google.TreeNode;
import DivideAndConquer.ListNode;

/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
/*
sortedListToBST_00 TC: O(nlogn) for n is size of input list.
                       we iterate list for logn times
sortedListToBST_01 TC: O(n) for n is size of input list (normally is traversal)
 */
public class ConvertSortedListToBST {

    private ListNode curListNode;

    public TreeNode sortedListToBST_01(ListNode head) {

        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);

        // Get size of the list.
        int sizeList = 0;
        ListNode ptr = head;
        while (ptr != null) {
            ptr = ptr.next;
            sizeList++;
        }

        curListNode = head;

        return inorder(0, sizeList - 1);
    }


    private TreeNode inorder(int start, int end) {

        if (start > end) {
            return null;
        }

        int mi = start + ((end - start) >> 1);

        TreeNode left = inorder(start, mi - 1);

        TreeNode curRootNode = new TreeNode(curListNode.val);
        curRootNode.left = left;
        curListNode = curListNode.next;

        TreeNode right = inorder(mi + 1, end);
        curRootNode.right = right;

        return curRootNode;
    }

    public TreeNode sortedListToBST_00(ListNode head) {

        // Base cases.

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }


        ListNode prevMiddle = findMiddleFront(head);
        ListNode middle = prevMiddle.next;
        prevMiddle.next = null;

        TreeNode root = new TreeNode(prevMiddle.val);
        root.left = sortedListToBST_00(head);
        root.right = sortedListToBST_00(middle);

        return root;
    }

    private ListNode findMiddleFront(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head, prev = slow;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }
}
