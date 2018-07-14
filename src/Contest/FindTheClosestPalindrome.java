package Contest;

/**
 * input : n
 * output: the closest integer which is a palindrome
 * (absolute dif minimized)
 * e.g.
 * 35716
 * <p>
 * 35653
 * 35753
 * 35853
 */
public class FindTheClosestPalindrome {
    public static void main(String[] args) {
        FindTheClosestPalindrome inst = new FindTheClosestPalindrome();
        inst.nearestPalindromic("10001");
    }

    public String nearestPalindromic(String n) {

        long nVal = Long.parseLong(n);
        // < 10 100 1000 10000
        if (nVal <= 10 || ((nVal % 10 == 0) && (n.charAt(0) == '1') && Integer.valueOf(n.substring(1)) == 0)) {
            return String.valueOf(nVal - 1);
        }
        // 11 101 1001 10001 100001
        if (nVal == 11 || ((nVal % 10 == 1) && (n.charAt(0) == '1') && (n.charAt(n.length() - 1) == '1') && Integer.valueOf(n.substring(1, n.length() - 1)) == 0)) {
            return String.valueOf(nVal - 2);
        }
        // 99 999 9999 99999
        boolean notAllNine = false;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '9') {
                notAllNine = true;
            }
        }
        if (!notAllNine && nVal >= 99) {
            return String.valueOf(nVal + 2);
        }
        String palindromeRoot = n.substring(0, (n.length() + 1) / 2);
        int valPalindromeRoot = Integer.valueOf(palindromeRoot);
        long tmpEqual = Long.parseLong(toPalindromeDigits(valPalindromeRoot, n.length() % 2 == 0));
        long tmpBigger = Long.parseLong(toPalindromeDigits(valPalindromeRoot + 1, n.length() % 2 == 0));
        long tmpSmaller = Long.parseLong(toPalindromeDigits(valPalindromeRoot - 1, n.length() % 2 == 0));
        long distEqual = Math.abs(nVal - tmpEqual);
        long distBigger = Math.abs(nVal - tmpBigger);
        long distSmaller = Math.abs(nVal - tmpSmaller);
        long distMin = 0L;
        if (distEqual == 0) {
            distMin = Math.min(distBigger, distSmaller);
        } else {
            distMin = Math.min(distEqual, Math.min(distBigger, distSmaller));
        }
        if (distMin == distSmaller) {
            return String.valueOf(tmpSmaller);
        }
        if (distMin == distEqual) {
            return String.valueOf(tmpEqual);
        }
        return String.valueOf(tmpBigger);
    }

    String toPalindromeDigits(int num, boolean isEvenDigits) {

        String numStr = String.valueOf(num);
        if (isEvenDigits) {
            return numStr + (new StringBuilder(numStr).reverse()).toString();
        }
        return numStr + (new StringBuilder(numStr).reverse().deleteCharAt(0)).toString();
    }
}
