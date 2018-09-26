package CompanyOriented.Facebook;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

/**
 * split list into k consecutive parts
 * split into consecutive parts with size difference at most 1,
 * and earlier parts are a larger size than the later parts.
 */
public class SplitLinkedListInParts {
    public static void main(String[] args) {
        SplitLinkedListInParts inst = new SplitLinkedListInParts();
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next = new ListNode(3);
        root.next = new ListNode(4);
        root.next = new ListNode(5);
        root.next = new ListNode(6);
        root.next = new ListNode(7);
        root.next = new ListNode(8);
        root.next = new ListNode(9);
        root.next = new ListNode(10);
        int k = 3;
        inst.splitListToParts(root, k);
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        int size = getSizeOfList(root); // 7
        int partSize = size / k > 0 ? size / k : 1; // 1
        int toBalance = size - partSize * k; //
        return buildResult(root, toBalance, partSize, k);
    }

    private int getSizeOfList(ListNode root) {
        int size = 0;
        ListNode ptr = root;
        while (ptr != null) {
            ptr = ptr.next;
            size++;
        }
        return size;
    }

    private ListNode[] buildResult(ListNode root, int toBalance, int partSize, int k) {

        ListNode ptr = root;
        ListNode[] result = new ListNode[k];
        int ri = 0;
        result[ri++] = ptr;
        ListNode prev = null;
        int counterWithinPair = 0;

        while (ptr != null && ri < k) {
            while (counterWithinPair < partSize) {
                counterWithinPair++;
                prev = ptr;
                ptr = ptr.next;
                if (ptr == null) {
                    break;
                }
            }
            if (ptr != null) {
                if (toBalance > 0) {
                    toBalance--;
                    prev = ptr;
                    ptr = ptr.next;
                }
                prev.next = null;
                result[ri++] = ptr;
                counterWithinPair = 0;
                continue;
            }
            if (prev != null) {
                result[ri++] = ptr;
            }
        }
        for (int i = ri; i < k; i++) {
            result[ri++] = ptr;
        }

        return result;
    }
}
