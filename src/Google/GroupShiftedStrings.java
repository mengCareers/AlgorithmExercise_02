package Google;

import java.util.*;

/**
 * input : strings
 * output: group all that belong to the same shifting sequence
 * their distance matters, simple save it
 * circular
 * group length ==, firstly
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String wordKey = getWordKey(str);
            if (!map.containsKey(wordKey)) {
                map.put(wordKey, new LinkedList<>());
            }
            map.get(wordKey).add(str);
        }
        List<List<String>> result = new ArrayList<>();
        for (String k : map.keySet()) {
            result.add(map.get(k));
        }
        return result;
    }

    private String getWordKey(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i++) {
            int tmp = str.charAt(i + 1) - str.charAt(i);
            if (tmp < 0) {
                tmp += 26;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }
}
