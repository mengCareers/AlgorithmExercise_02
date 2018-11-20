package CompanyOriented.Google.PhoneInterview;

/*

 */
public class KMP {
    // abcabcd
    //     i
    public static void main(String[] args) {
        String t = "AACAAB", s = "AAACAAB";
        kmpSearch(s, t);
    }

    public static void kmpSearch(String s, String t) {
        int[] lps = computeLPS(t);
        int i = 0, j = 0, sLength = s.length(), tLength = t.length();
        while (i < sLength) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            if (j == tLength) {
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                j = lps[j - 1];
                continue;
            }
            if (t.charAt(j) != s.charAt(i)) {
                if (j == 0)
                    i++;
                else
                    j = lps[j - 1];
            }
        }
    }

    private static int[] computeLPS(String t) {
        int tLength = t.length(), i = 1, preLengthLPS = 0;
        int[] lps = new int[tLength];
        lps[0] = 0;
        while (i < tLength) {
            if (t.charAt(i) == t.charAt(preLengthLPS)) {
                preLengthLPS++;
                lps[i] = preLengthLPS;
                i++;
            } else {
                if (preLengthLPS != 0)
                    preLengthLPS = lps[preLengthLPS - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
