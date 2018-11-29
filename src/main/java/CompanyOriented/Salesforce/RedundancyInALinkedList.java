package CompanyOriented.Salesforce;

import java.util.HashSet;
import java.util.Set;

public class RedundancyInALinkedList {
    public static Node distinct(Node root) {
        if (root == null)
            return root;

        Node prev = root, cur = root;
        Set<Integer> valSet = new HashSet<>();

        while (cur != null) {
            while (valSet.contains(cur.val)) {
                prev.next = cur.next;
                if (cur.next == null)
                    break;
                cur = prev.next;
            }
            valSet.add(cur.val);
            prev = cur;
            cur = cur.next;
        }

        return root;
    }

    /*
    1 0 2
        p
        c
     */
    public static Node distinct1(Node root) {
        if (root == null)
            return root;
        Node dummyNode = new Node(0);
        dummyNode.next = root;
        Node prev = root, cur = root;
        while (cur.next != null) {
            cur = cur.next;
            while (cur != null && prev.val == cur.val) {
                cur = cur.next;
            }
            if (cur == null) {
                prev.next = null;
                return dummyNode.next;
            }
            if (prev.val != cur.val) {
                prev = cur;
            }
        }
        return dummyNode.next;
    }


}
/*
1 0 1
    x
 */
/*
0 1 1 1
  p
        c

  c++
  if (p != c)
    p++
  else
    while (p == c)
        c++
    p = c
 */

