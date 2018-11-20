package DynamicProgramming;

/**
 * There are 3 possiblities to satisify one edit distance apart:
 * Insert a character into s to get t, when slen < tlen
 * Delete a character from s to get t, when slen > tlen
 * Replace a character of s to get t, when slen = tlen
 */
public class OneEditDistance {
    public boolean isOneEdit(String s, String t) {
        if (s.length() > t.length()) {
            return isOneEdit(t, s);
        }
        int slen = s.length();
        int tlen = t.length();
        int i = 0;
        for (; i < Math.min(slen, tlen); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (slen < tlen) {
                    return s.substring(i).equals(t.substring(i + 1));
                } else if (slen > tlen) {
                    return s.substring(i + 1).equals((t.substring(i)));
                } else {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
            }
        }
        return Math.max(slen, tlen) == i + 1;
    }
}
