package CompanyOriented.Pinterest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
 * Given a streaming log in the format of [timestamp, key, value],
 * return the key with the largest sum of value in current sliding window - which is a 300s-window.
 * */
/*
 * Thought:
 * Since we ought to get an aggregation on records, we may save values needed in a Data Structure.
 * Since we aggregate by a single key, we may map records to key.
 * Which? Queue<Record> for timestamp increasingly, and we remove from head, append to end to simulate sliding window.
 *        Map<key, ValueInfo> to aggregate key by key.
 *
 * We assume no Integer Overflow on sum(values); records with same timestamp and key are valid input.
 *
 * */
public class HitCounter {

    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.readLog();

    }

    private static Deque<Record> queue; // Simulation of sliding window.
    private static Map<String, ValueInfo> keyToValueInfo; // Key: key, value: object ValueInfo.
    private static String keyWithMaxSumValue; // Key with maximum sum of value currently.

    public HitCounter() {
        queue = new ArrayDeque<>();
        keyToValueInfo = new HashMap<>();
        keyWithMaxSumValue = null;
    }


    public void readLog() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                // Parse line : e.g. "10000 key0 5" and save into Record object.
                String[] splits = line.split(" ");
                long curTime = Long.parseLong(splits[0]);
                boolean isMaxRemoved = false;
                if (!queue.isEmpty())
                    // Remove expired records.
                    isMaxRemoved = removeExpired(curTime - 300);
                // Offer newly read records.
                offerNewly(curTime, splits[1], Integer.parseInt(splits[2]), isMaxRemoved);
                System.out.println(keyWithMaxSumValue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void offerNewly(long curTime, String key, int value, boolean isMaxRemoved) {
        queue.offer(new Record(curTime, key, value));
        keyToValueInfo.putIfAbsent(key, new ValueInfo(new HashMap<>(), 0));
        ValueInfo valueInfo = keyToValueInfo.get(key);
        valueInfo.getMap().put(curTime, valueInfo.getMap().getOrDefault(curTime, 0) + value);
        valueInfo.setTotal(valueInfo.getTotal() + value);
        if (!isMaxRemoved) {
            // Compare with keyWithMaxSumValue to get current Max.
            if (keyWithMaxSumValue == null || keyToValueInfo.get(keyWithMaxSumValue).getTotal() < valueInfo.getTotal())
                keyWithMaxSumValue = key;
        } else {
            // Iterate to get current Max.
            int maxTotal = Integer.MIN_VALUE;
            String maxKey = null;
            for (String mapKey : keyToValueInfo.keySet()) {
                int curTotal = keyToValueInfo.get(mapKey).getTotal();
                if (curTotal > maxTotal) {
                    maxTotal = curTotal;
                    maxKey = mapKey;
                }
            }
            keyWithMaxSumValue = maxKey;
        }
    }

    /*
     * Return true if keyWithMaxSumValue is expired.
     * */
    private boolean removeExpired(long expiredTime) {
        boolean isMaxRemoved = false;
        while (queue.peek().getTimestamp() <= expiredTime) { // Records of the exact expiredTime are considered as expired as well.
            Record expiredRecord = queue.poll();

            String key = expiredRecord.getKey();
            int val = expiredRecord.getValue();
            long ts = expiredRecord.getTimestamp();
            ValueInfo valueInfo = keyToValueInfo.get(key);
            int curVal = valueInfo.getMap().get(ts) - val;
            if (curVal == 0)
                valueInfo.getMap().remove(ts);
            else
                valueInfo.getMap().put(ts, curVal);
            int curTotal = valueInfo.getTotal() - val;
            if (curTotal == 0)
                keyToValueInfo.remove(key);
            else
                valueInfo.setTotal(curTotal);

            if (keyWithMaxSumValue.equals(key))
                isMaxRemoved = true;
        }
        return isMaxRemoved;
    }

}

class Record {
    private long timestamp;
    private String key;
    private int value;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Record(long timestamp, String key, int value) {
        this.timestamp = timestamp;
        this.key = key;
        this.value = value;
    }

//    public boolean equals(Record record) {
//        return (timestamp == record.getTimestamp())
//                && (key.equals(record.getKey()))
//                && (value == record.getValue());
//    }
}

class ValueInfo {
    private Map<Long, Integer> map; // Key: timestamp, value: corresponding sum of value.
    private int total; // Sum of values of the key in current queue. (MAY CAUSE INTEGER_OVERFLOW)

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<Long, Integer> getMap() {
        return map;
    }

    public ValueInfo(Map<Long, Integer> map, int total) {
        this.map = map;
        this.total = total;
    }
}
