package CompanyOriented.Google;

import java.util.HashSet;
import java.util.Set;

public class ImplementMagicDictionary {
    Set<String> set;

    public ImplementMagicDictionary() {
        set = new HashSet<>();
    }

    public void buildDict(String[] dict) {
        for (String word : dict) {
            set.add(word);
        }
    }

    public boolean search(String word) {
        for (String str : set) {
            if (isOneReplace(str, word)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOneReplace(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return (i == s.length() - 1) || (s.substring(i + 1).equals(t.substring(i + 1)));
            }
        }
        return false;
    }
}
