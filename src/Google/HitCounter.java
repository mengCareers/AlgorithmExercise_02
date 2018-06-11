package Google;

import java.util.HashMap;
import java.util.Map;

/**
 It is inductive to maintain a data structure that stores hits at each timestamp. We use the map <key : timestamp, value : hits at the timestamp>.
 When getHits(), we accumulate the sum of hits in the last 5 minutes.
 */
public class HitCounter {
    Map<Integer, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        map = new HashMap<>();
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int sumHits = 0;
        for (int t = timestamp; t > timestamp - 300; t--) {
            sumHits += map.getOrDefault(t, 0);
        }
        return sumHits;
    }
}
