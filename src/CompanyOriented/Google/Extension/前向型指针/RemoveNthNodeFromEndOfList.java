package CompanyOriented.Google.Extension.前向型指针;

import DivideAndConquer.ListNode;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode dummyHead = new ListNode(0), s = dummyHead, e = s;
        dummyHead.next = head;

        for (int i = 0; i < n; i++) {
            e = e.next;
        }
        while (e.next != null) {
            e = e.next;
            s = s.next;
        }
        s.next = s.next.next;

        return dummyHead.next;
    }
}
