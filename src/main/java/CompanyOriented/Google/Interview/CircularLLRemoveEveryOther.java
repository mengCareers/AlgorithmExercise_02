package CompanyOriented.Google.Interview;

import DivideAndConquer.ListNode;

public class CircularLLRemoveEveryOther {
    /*
    1 2 3 4
 prev p
     */
    public static void removeEveryOther(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode ptr = head.next;
        ListNode prev = head;
        while (ptr != head) { // prev -> ptr
            prev.next = ptr.next;
            prev = ptr.next;
            if(prev == head)
                return;
            ptr = ptr.next.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode ptr = head;
        int i = 2;
        while (i < 6) {
            ptr.next = new ListNode(i++);
            ptr = ptr.next;
        }
        ptr.next = head;
        printList(head);
        System.out.println();
        removeEveryOther(head);
        printList(head);
    }

    public static void printList(ListNode head) {
        ListNode ptr = head;
        while (true) {
            System.out.print(ptr.val + ", ");
            if(ptr.next == head)
                return;
            ptr = ptr.next;
        }

    }
}
