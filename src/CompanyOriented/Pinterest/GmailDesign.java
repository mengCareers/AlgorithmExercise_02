package CompanyOriented.Pinterest;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU
 * Insert, delete, linkedlist
 * Singly delete O(2n)
 * Doubly O(n)
 * Node {
 * id;
 * content;
 * }
 * Map<id, Node>
 */
public class GmailDesign {


    class Node {
        String id;
        String content;
        Node prev, next;

        public Node(String id, String content) {
            this.id = id;
            this.content = content;
            prev = null;
            next = null;
        }
    }

    private static Map<String, Node> map;
    private static int capacity;
    Node dummyHead, dummyTail;

    public GmailDesign(int capacity) {
        map = new HashMap<>(); // key: id, value: node.
        dummyHead = new Node("", "");
        dummyTail = new Node("", "");
        dummyHead.next = dummyTail;
        dummyTail.next = dummyHead;
        this.capacity = capacity;
    }

    /*
    Get content by id.
     */
    public String get(String id) {
        if (!map.containsKey(id)) {
            return "";
        }
        // If exist.

        Node node = map.get(id);
        removeNode(node);
        addToTail(node);
        return node.content;
    }

    public void put(String id, String content) {
        // If exist.
        Node node;
        if (map.containsKey(id)) {
            node = map.get(id);
            node.content = content;
            removeNode(node);
            addToTail(node);
        }
        // If not exist.
        else {
            if (map.size() == capacity) {
                removeNode(dummyHead.next);
            }
            node = new Node(id, content);
            map.put(id, node);
            addToTail(node);
        }
    }

    private void removeNode(Node node) {

    }

    private void addToTail(Node node) {

    }
}
