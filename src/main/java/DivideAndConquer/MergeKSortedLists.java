package DivideAndConquer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static void main(String[] args) {
        MergeKSortedLists inst = new MergeKSortedLists();
        ListNode[] lists = new ListNode[2];
        lists[0] = null;
        lists[1] = new ListNode(1);
        inst.mergeKLists(lists);
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        return sortByPartition(lists, 0, lists.length - 1);
    }

    private ListNode sortByPartition(ListNode[] lists, int start, int end) {

        if (start == end) { // base case
            return lists[start];
        }

        int middle = start + (end - start) / 2;
        ListNode left = sortByPartition(lists, start, middle);
        ListNode right = sortByPartition(lists, middle + 1, end);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {

        if (left == null || right == null) { // base case
            return left == null ? right : left;
        }

        if (left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }

    }

    public ListNode mergeKListsPQ(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode result = new ListNode(0);
        ListNode ptr = result;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode curNode : lists) {
            if (curNode != null)
                priorityQueue.add(curNode);
        }
        ListNode curNode = null;
        while (!priorityQueue.isEmpty()) {
            curNode = priorityQueue.poll();
            ptr.next = curNode;
            ptr = ptr.next;
            if (curNode.next != null) {
                priorityQueue.add(curNode.next);
                curNode.next = null;
            }
        }

        return result.next;
    }
}
