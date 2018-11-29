package CompanyOriented.Google.Interview;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

interface Cache<K, V> {

    /* After timespan, the entry is expired. */
    void set(long timespan, K key, V value);

    V get(K key);
}

public class ExpirationHashMap implements Cache<String, Integer> {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private Map<String, Node> map = new ConcurrentHashMap<>();

    private final long PRUNE_INTERVAL = 1;

    public ExpirationHashMap() {
        startPruning();
    }

    @Override
    public synchronized void set(long timespan, String key, Integer value) {
        map.put(key, new Node(getCurrentTime() + timespan, value));
    }

    @Override
    public Integer get(String key) {
        Node node = map.get(key);
        if (node == null)
            return null;
        if (node.expirationTime <= getCurrentTime()) {
            map.remove(key);
            return null;
        }
        return node.val;
    }

    public void startPruning() {
        scheduler.scheduleAtFixedRate(() -> {
            prune(getCurrentTime());
        }, 0, PRUNE_INTERVAL, TimeUnit.MILLISECONDS);
    }

    public synchronized void prune(long currentTime) {
        Iterator<Map.Entry<String, Node>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Node> entry = iterator.next();
            Node node = entry.getValue();
            if (node.expirationTime <= currentTime) {
                iterator.remove();
            }
        }
    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private static class Node<String, Integer> {
        long expirationTime;
        int val;

        public Node(long expirationTime, int val) {
            this.expirationTime = expirationTime;
            this.val = val;
        }
    }
}
/*
set, <K,V> after curTime + timespan expired
get, curTime, loop through the map to remove expired ones
Q:
    how about set multiple times?
        update curTime + timespan

    <K, <ts, V>>    m2. <K, pq<node>> node {ts, V}  next expired
e.g.<a, <1001,1>>

get(a), curTime = 1002
   remve <1001, 1>
  return null;

renew current cache
 */