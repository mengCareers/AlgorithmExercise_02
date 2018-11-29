package CompanyOriented.Google.Interview;

/*
Implement a doubly linked list and remove the first one of the target key.
 */
public class DoublyLL {

    int n;
    Node dummyHead, dummyTail;

    public DoublyLL() { // DoublyLL
        n = 0;
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void add(int val) {
        // - dummyTail.prev - newNode - dummyTail
        Node newNode = new Node();
        newNode.val = val;
        newNode.next = dummyTail;
        Node dummyTailPrev = dummyTail.prev;
        dummyTail.prev = newNode;
        dummyTailPrev.next = newNode;
        newNode.prev = dummyTailPrev;
        n++;
    }


    public void removeNodeValueTarget(int target) {
        Node ptr = dummyHead.next;
        // dummyhead - x - dummytail
        //            ptr
        while (ptr != dummyTail) {
            if (ptr.val == target) {
                // ptrprev - ptr - ptrnext
                ptr.prev.next = ptr.next;
                ptr.next.prev = ptr.prev;
                return;
            }
            ptr = ptr.next;
        }
    }
}


class Node {
    Node prev;
    Node next;
    int val;
}
