package Google;

import java.util.HashMap;
import java.util.Map;

/**
 * input:  s, t
 * output: true if they are isomorphic
 * isomorphic: all occurrences of a char must be replaced with another char while preserving the order
 * e.g. paper
 * p - t
 * see if all p is replaced by t, others not
 * m2.
 * loop through s
 * map <char, linked list of positions>
 * loop through t
 * list remove
 * if successfully, true
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        return isMatched(s, t) && isMatched(t, s);
    }

    private boolean isMatched(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        char chS = 0, chT = 0;
        for (int i = 0; i < s.length(); i++) {
            chS = s.charAt(i);
            chT = t.charAt(i);
            if (!map.containsKey(chS)) {
                map.put(chS, chT);
            } else {
                if (map.get(chS) != chT) {
                    return false;
                }
            }
        }
        return true;
    }
}
