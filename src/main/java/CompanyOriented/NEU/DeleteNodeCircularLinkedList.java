package CompanyOriented.NEU;

import CompanyOriented.Salesforce.Node;

public class DeleteNodeCircularLinkedList {

    private static Node head;

    public static Node deleteNodeInCircularLinkedList(Node headNode, int target) {
        head = headNode;
        deleteUtil(target);

        return head;
    }


    private static void deleteUtil(int target) {

        /* Corner cases. */
        if (head == null) {
            return;
        }
        if (head.next == head) {
            if (head.val == target)
                head = null;
            return;
        }

        /* Find the last node of the list, i.e. the one in front of head. */
        Node tail = head;
        while (tail.next != head)
            tail = tail.next;

        Node curr = head, prev = tail;
        while (curr.val != target) {

            /* If there is no elements to delete. */
            if (curr.next == head) {
                return;
            }

            prev = curr;
            curr = curr.next;
        }

        /* If we are about to delete head. */
        if (curr == head)
            head = curr.next;

        prev.next = curr.next;

        deleteUtil(target);
    }
}