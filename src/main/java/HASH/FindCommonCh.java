package HASH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindCommonCh {
    public List<String> commonChars(String[] A) {
        // index in A,
        List<Map<Character, Integer>> dictList = buildMapList(A);

        // Build chToFreq.
        Map<Character, Integer> chToFreq = new HashMap<>();
        Set<Character> commonChars = new HashSet<>();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (Map<Character, Integer> dict : dictList) {
                if (dict.keySet().contains(ch)) commonChars.add(ch);
            }
        }

        for (char ch : commonChars) {
            if (!chToFreq.containsKey(ch)) chToFreq.put(ch, Integer.MAX_VALUE);
            for (Map<Character, Integer> dict : dictList) {
                chToFreq.put(ch, Math.min(chToFreq.get(ch), dict.get(ch)));
            }
        }

        // Build result.
        List<String> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : chToFreq.entrySet()) {
            String key = "" + entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                result.add(key);
            }
        }

        return result;
    }

    private List<Map<Character, Integer>> buildMapList(String[] A) {
        List<Map<Character, Integer>> dictList = new ArrayList<>();

        for (String word : A) {
            Map<Character, Integer> dict = buildMap(word);
            dictList.add(dict);
        }

        return dictList;
    }

    private Map<Character, Integer> buildMap(String word) {
        Map<Character, Integer> dict = new HashMap<>();
        for (char ch : word.toCharArray()) {
            dict.put(ch, dict.getOrDefault(ch, 0) + 1);
        }
        return dict;
    }
}
