package CompanyOriented.Pinterest;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/*
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 */
/*
Since we need to get/put, should be a key-value map.
To encapsulate Node and delete/insert in O(n), we use doubly linked list
the node saving all keys with the same couns in a LinkedHashSet
Since we need to modify list(LinkedHash, contains, insertion order) using key, we noode a key-node

 */
public class LFUCache {

    private Node dummyHead, dummyTail;
    private int capacity;
    private Map<Integer, Integer> keyToValue; // key: key, value: value.
    private Map<Integer, Node> keyToNode; // key: key, value: node;

    public LFUCache(int capacity) {

        // For doubly linked list.
        dummyHead = new Node(0);
        dummyTail = new Node(0);
        dummyHead.next = dummyTail;
        dummyTail.next = dummyHead;

        this.capacity = capacity;
        keyToValue = new HashMap<>();
        keyToNode = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToValue.containsKey(key)) {
            return -1;
        }
        // Increase frequency.
        return keyToValue.get(key);
    }

    public void put(int key, int value) {
        if (!keyToValue.containsKey(key)) {
            if (keyToValue.size() == capacity) {
                // Remove least frequent node
            }
            // Add node to head
        }
        keyToValue.put(key, value);


        // Increase frequency.
    }

    class Node {
        int freq;
        LinkedHashSet<Integer> keys;
        Node prev;
        Node next;

        public Node(int freq) {
            this.freq = freq;
            keys = new LinkedHashSet<>();
            prev = next = null;
        }
    }
}
