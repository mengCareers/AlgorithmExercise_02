package CompanyOriented.Google.Interview;

import java.util.*;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        return isMatched(s, t) && isMatched(t, s);
    }

    private boolean isMatched(String s, String t) {
        Map<Character, Character> map = new HashMap<>(); // key: char in s, value: char in t
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char schar = s.charAt(i), tchar = t.charAt(i);
            if (map.containsKey(schar)) {
                if (map.get(schar) != tchar)
                    return false;
            }
            map.put(schar, tchar);
        }
        return true;
    }

}
