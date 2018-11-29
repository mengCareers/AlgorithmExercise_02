import CompanyOriented.NEU.DeleteNodeCircularLinkedList;
import CompanyOriented.Salesforce.Node;
import org.junit.jupiter.api.Test;

public class NEU {
    @Test
    public void testDeleteNodeCircularLinkedList_ZeroNodeInList() {
        Node headNode = buildCircularList(new int[0]);
        headNode = DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 1);
        printList(headNode);
    }

    @Test
    public void testDeleteNodeCircularLinkedList_DeleteNodeNotInList() {
        Node headNode = buildCircularList(new int[]{1, 3, 4});
        headNode = DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 2);
        printList(headNode);
    }

    @Test
    public void testDeleteNodeCircularLinkedList_DeleteHeadNodesInList() {
        Node headNode = buildCircularList(new int[]{1, 1, 2});
        headNode = DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 1);
        printList(headNode);
    }

    @Test
    public void testDeleteNodeCircularLinkedList_DeleteTailNodesInList() {
        Node headNode = buildCircularList(new int[]{1, 2, 2});
        DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 2);
        printList(headNode);
    }

    @Test
    public void testDeleteNodeCircularLinkedList_DeleteMiddleNodesInList() {
        Node headNode = buildCircularList(new int[]{1, 2, 2, 3});
        DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 2);
        printList(headNode);
    }

    @Test
    public void testDeleteNodeCircularLinkedList_DeleteHeadTailNodesInList() {
        Node headNode = buildCircularList(new int[]{1, 2, 1});
        headNode = DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 1);
        printList(headNode);
    }

    @Test
    public void testDeleteNodeCircularLinkedList_DeleteAllNodesInList() {
        Node headNode = buildCircularList(new int[]{1, 1, 1, 1});
        headNode = DeleteNodeCircularLinkedList.deleteNodeInCircularLinkedList(headNode, 1);
        printList(headNode);
    }


    private Node buildCircularList(int[] values) {
        if (values == null || values.length == 0)
            return null;

        Node head = new Node(values[0]), prev = head, curr;
        int i = 1, n = values.length;

        while (i < n) {
            curr = new Node(values[i]);
            prev.next = curr;
            prev = curr;
            i++;
        }

        /* Make tail node point to head. */
        prev.next = head;

        return head;
    }

    private void printList(Node head) {
        if (head == null) {
            return;
        }

        Node ptr = head;
        do {
            System.out.print(ptr.val + " -> ");
            ptr = ptr.next;
        } while (ptr != head);
    }

}
