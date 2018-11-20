package CompanyOriented.Amazon;

import java.util.HashMap;
import java.util.Map;

/*
with random pointer
 */
/*
use map to help copy
 */
public class DeepCopyLinkedList {
    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) {
            return head;
        }

        // 1 -> 2 -> 3 -> N to 1 -> 1 -> 2 -> 2 -> 3 -> 3 -> N
        RandomListNode ptr = head, nxt, dummyHead, nptr;
        while (ptr != null) {
            nxt = ptr.next;
            ptr.next = new RandomListNode(ptr.label);
            ptr.next.next = nxt;
            ptr = nxt;
        }

        // do with random pointers
        ptr = head;
        while (ptr != null) {
            if (ptr.random != null)
                ptr.next.random = ptr.random.next;
            ptr = ptr.next.next;
        }

        // separate copy from original
        ptr = head;
        dummyHead = new RandomListNode(0);
        nptr = dummyHead;

        while (ptr != null) {
            nxt = ptr.next.next;

            nptr.next = ptr.next;
            nptr = ptr.next;

            ptr.next = nxt;
            ptr = nxt;
        }

        return dummyHead.next;
    }

    //
    public RandomListNode copyRandomList_Map(RandomListNode head) {

        if (head == null) {
            return head;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode ptr = head;
        RandomListNode newNode;
        while (ptr != null) {
            newNode = new RandomListNode(ptr.label);
            map.put(ptr, newNode);
            ptr = ptr.next;
        }
        RandomListNode copyNode, copyNext, copyRandom;
        for (RandomListNode oriNode : map.keySet()) {
            map.get(oriNode).next = map.get(oriNode.next);
            map.get(oriNode).random = map.get(oriNode.random);
        }
//        ptr = head;
//        map.put(null, null);
//        while (ptr != null) {
//            map.get(ptr).next = map.get(ptr.next);
//            map.get(ptr).random = map.get(ptr.random);
//            ptr = ptr.next;
//        }

        return map.get(head);
    }

    public RandomListNode copyRandomList_1(RandomListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        RandomListNode dummyHead = head;
        RandomListNode ptr = head;
        RandomListNode newNode;
        RandomListNode oriNext;
        while (ptr != null) {
            newNode = new RandomListNode(ptr.label);
            oriNext = ptr.next;
            ptr.next = newNode;
            newNode.next = oriNext;
            ptr.next.random = ptr.random.next;
            ptr = ptr.next;
        }

        ptr = head.next;
        while (ptr != null) {
            ptr.next = ptr.next.next;
            ptr = ptr.next;
        }

        return ptr;
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        label = x;
    }
}
