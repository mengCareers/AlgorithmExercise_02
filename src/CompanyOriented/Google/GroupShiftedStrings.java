package CompanyOriented.Google;

import java.util.*;

/**
 * input : strings
 * output: group all that belong to the same shifting sequence
 * their distance matters, simple save it
 * circular
 * group length ==, firstly
 */
public class GroupShiftedStrings {
    public static void main(String[] args) {
        GroupShiftedStrings inst = new GroupShiftedStrings();
        String[] strings = {"ba", "az"};
        inst.groupStrings(strings);
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String str : strings) {
            String strKey = getKey(str.toCharArray());
            if (!map.containsKey(strKey)) {
                map.put(strKey, new ArrayList<>());
            }
            map.get(strKey).add(str);
        }
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    private String getKey(char[] strArr) {
        StringBuilder key = new StringBuilder();
        int difference = 0;
        for (int i = 0; i < strArr.length - 1; i++) {
            difference = strArr[i + 1] - strArr[i];
            if (difference < 0) {
                difference += 26;
            }
            key.append(difference);
        }
        return key.toString();
    }
}
