package CompanyOriented.Salesforce;

import java.util.HashMap;
import java.util.Map;

/**
 * input: int[] nums
 * dup is >= 2 identical elements
 * output: # of dup
 */
public class CountDup {
    public int countDup(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int numDups = 0;

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1)
                numDups++;
        }

        return numDups;
    }
}