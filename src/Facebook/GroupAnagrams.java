package Facebook;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 */
public class GroupAnagrams {
    /*
     * for each str, build a map for it
     * e.g. e a t
     *      t e a
     * group rule - with the same character set
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String groupKey = getGroupKey(str);
            if (!map.containsKey(groupKey)) {
                map.put(groupKey, new ArrayList<>());
            }
            map.get(groupKey).add(str);
        }
        for (String mapKey : map.keySet()) {
            result.add(map.get(mapKey));
        }
        return result;
    }

    private String getGroupKey(String s) {
        char[] chArr = s.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }
}
