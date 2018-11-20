package CompanyOriented.Google.DataStructure;

import java.util.Map;

/**
 * As a hashmap, it should have ENTRY<key, value></key,>
 * Key is made unique by hash function : KEY.HASHCODE()/ENTRY.LENGTH
 * However, there may be conflict
 * I decide to solve it by Looking_For_Next_Empty or LINKEDLIST_OF_LINKED_LIST
 */
class Entry {
    Object key;
    Object value;
    Entry next;

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}

public class MyHashMap<K, V> {

    final static int DEFAULT_CAPCITY = 16;
    Entry[] storage;

    public MyHashMap() {
        storage = new Entry[DEFAULT_CAPCITY];
    }

    public int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }


    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }
        return get(key) != null;
    }

    public void put(Object key, Object value) {
        if (key == null) {
            return;
        }
        int index = hash(key);
        Entry ptr = storage[index];
        while (ptr != null) {
            if (ptr.key == key) {
                ptr.value = value;
                return;
            }
            ptr = ptr.next;
        }
        Entry head = new Entry(key, value);
        head.next = storage[index];
        storage[index] = head;
    }

    public Object get(Object key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        Entry ptr = storage[index];
        while (ptr != null) {
            if (ptr.key == key) {
                return ptr.value;
            }
            ptr = ptr.next;
        }
        return null;
    }

}
