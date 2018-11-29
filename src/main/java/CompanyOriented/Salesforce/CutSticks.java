package CompanyOriented.Salesforce;

import java.util.Map;
import java.util.TreeMap;

/**
 * input: int[] lengths,
 * remove all shortest length from all sticks after each turn
 * output: int[] turnStartSticks
 */
public class CutSticks {
    public static int[] cutSticks(int[] lengths) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int length : lengths) {
            map.put(length, map.getOrDefault(length, 0) + 1);
        }
        int total = lengths.length;
        int[] count = new int[map.size()];
        count[0] = total;
        int i = 1;

        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.firstEntry();
            total -= entry.getValue();
            if (total == 0)
                break;
            map.remove(entry.getKey());
            count[i++] = total;
        }

        return count;
    }
}
/*
e.g. 1 1 2 3
     0 0 1 2
     0 0 0 1
     0 0 0 0

    get min
    cut min from all
    put into TreeMap: <1, 2> <2, 1> <3, 1>
int lastTimeDiff = 0

 */
