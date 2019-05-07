package Recursion;

public class CheckIfWordValidAfterSubstitution {
    public static void main(String[] args) {
        CheckIfWordValidAfterSubstitution inst = new CheckIfWordValidAfterSubstitution();
        System.out.println(inst.isValid("aabcbc"));
    }

    private static final String valid = "abc";
    private int n;


    public boolean isValid(String S) {
        StringBuilder sb = new StringBuilder(S);
        while (sb.indexOf("abc") != -1) {
            int index = sb.indexOf("abc");
            sb.delete(index, index + 3);
        }
        return sb.length() == 0 ? true : false;
    }

    private boolean isValid(String S, int start, int end) {
        if (end - start < 2) return false;
        int i = start;
        while (i + 3 <= end + 1) {
            // abcabc || a abc bc
            //           i      j
            if (S.substring(i, i + 3).equals(valid)) {
                i += 3;
            } else {
                break;
            }
        }
        if (i == end + 1) return true;

        int j = end;
        while (j - 2 >= start) {
            if (S.substring(j - 2, j + 1).equals(valid)) {
                j -= 3;
            } else {
                break;
            }
        }

        if ((S.substring(i, i + 2) + S.charAt(j)).equals(valid)) {
            if (isValid(S, i + 2, j - 1)) return true;
        }

        if ((S.charAt(i) + S.substring(j - 1, j + 1)).equals(valid)) {
            if (isValid(S, i + 1, j - 2)) return true;
        }

        return false;
    }
}
/*
* valid if S =
*  abc is valid
*  X + Y == V
*  a + bc = abc
*
*  a + "abc" + bc also valid
*  ab + "abc" + c
*
*  only one not
*  ... abc ...B
*  A ends with non-abc
*  B ends with __c
* */
