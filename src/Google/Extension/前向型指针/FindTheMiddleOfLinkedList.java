package Google.Extension.前向型指针;

import DivideAndConquer.ListNode;

public class FindTheMiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
