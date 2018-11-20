package Contest;

import java.util.Arrays;

public class GroupsOfSpecialEquiStrings {
    public int numSpecialEquivGroups(String[] A) {
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(isSpecialEquivGroups("cdab", "adcb"));
    }

    /*
    get odd chars of S, odd chars of T, if equal, true
    get even chars of S, even chars of T, if equal, true
    */
    private static boolean isSpecialEquivGroups(String S, String T) {
        if (S.length() != T.length()) {
            return false;
        }
        int len = S.length();
        char[] arrayS = S.toCharArray(); // if len = 5, 0 1 2 3 4
        char[] arrayT = T.toCharArray();

        int[] evenCharsT = new int[26], oddCharsT = new int[26];
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                evenCharsT[arrayT[i] - 'a']++;
                continue;
            }
            oddCharsT[arrayT[i] - 'a']++;
        }
        int[] evenCharsS = new int[26], oddCharsS = new int[26];
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                evenCharsS[arrayS[i] - 'a']++;
                continue;
            }
            oddCharsS[arrayS[i] - 'a']++;
        }
        return (Arrays.equals(evenCharsT, evenCharsS)) && (Arrays.equals(oddCharsT, oddCharsS));
    }
}
