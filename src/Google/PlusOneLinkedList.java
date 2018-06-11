package Google;

/**
 * add one to last node
 * if 9, carry 1
 * reverse ?
 * 9 2 1
 * 0 3 1
 * reverse
 */
public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newHead = reverseList(head);
        ListNode ptr = newHead;
        ptr.val += 1;
        while (ptr.val == 10) {
            ptr.val = 0;
            if (ptr.next != null) {
                ptr = ptr.next;
                ptr.val += 1;
            } else {
                ptr.next = new ListNode(1);
                ptr = ptr.next;
            }
        }
        return reverseList(newHead);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode nxt = null;
        while (cur != null) {
            nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        head.next = null;
        return prev;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
