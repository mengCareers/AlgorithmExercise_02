package CompanyOriented.Google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * rules :
 * following the form <first_letter><number><last_letter>
 * input : dictionary, word
 * output: whether its abbrev is unique in dictionary
 */
public class UniqueWordAbbreviation {

    Map<String, Set<String>> map;

    public UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        String abbr = "";
        for (String word : dictionary) {
            abbr = getAbbr(word);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new HashSet<>());
            }
            map.get(abbr).add(word);
        }
    }

    private String getAbbr(String word) {
        if (word == null || word.length() == 0) {
            return word;
        }
        StringBuilder abbr = new StringBuilder();
        abbr.append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1));
        return abbr.length() > word.length() ? word : abbr.toString();
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        return
                (!map.containsKey(abbr)
                        || (map.get(abbr).size() == 1 && map.get(abbr).contains(word)));
    }
}
