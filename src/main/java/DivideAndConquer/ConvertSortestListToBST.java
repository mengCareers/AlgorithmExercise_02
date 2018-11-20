package DivideAndConquer;

import CompanyOriented.Google.TreeNode;

/*
109. Convert Sorted List to Binary Search Tree
 */
public class ConvertSortestListToBST {
    public TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode slowPtr = head, fastPtr = head, preSlowPtr = null;
        while (fastPtr != null && fastPtr.next != null) {
            preSlowPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        TreeNode root = new TreeNode(slowPtr.val);

        if (preSlowPtr == null) {
            return root;
        }

        preSlowPtr.next = null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slowPtr.next);

        return root;
    }
}
