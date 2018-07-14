package Contest;

/**
 * we get TLE,
 * we have two filters, isPrime and isPalindrome, which can filter more data?
 * isPalindrome, for there are fewer numbers satisfy isPalindrome
 * so we get all palindromes bigger than N, (by 564. Find the Closest Palindrome)
 * and return the first one who isPrime.
 */

/**
 * Actually, all palindromes with an even number of digits are divisible by 11, and are therefore not prime! (Except for 11.)
 * Each palindrome of dd digits has a palindromic root, it's first k = (d + 1) / 2
 * roots and palindromes are not a bijection
 * ​
 * ​d+1
 * ​​  digits. The next palindrome is formed by the next root.
 */
public class PrimePalindrome {

    public static void main(String[] args) {
        PrimePalindrome inst = new PrimePalindrome();
        System.out.println(inst.primePalindrome(6));
    }

    public int primePalindrome(int N) {
        // getAllPalindromeAfter N e.g. 130
        // 10000 10000
        for (int L = 1; L <= 5; L++) {
            // 1 10
            // 10 100  35  K = L - 2, L = 2, K = 0
            // 100 1000  1232   K = L - 2, L = 3, K = 1
            for (int palindromeRoot = (int) Math.pow(10, L - 1); palindromeRoot < (int) Math.pow(10, L); palindromeRoot++) {
                StringBuilder strPrimePalindrome = new StringBuilder(String.valueOf(palindromeRoot));
                for (int k = L - 2; k >= 0; k--) {
                    strPrimePalindrome.append(strPrimePalindrome.charAt(k));
                }
                int valPrimePalindrome = Integer.parseInt(strPrimePalindrome.toString());
                if (valPrimePalindrome >= N && isPrime(valPrimePalindrome)) {
                    if (N <= 11) {
                        return Math.min(valPrimePalindrome, 11);
                    }
                    return valPrimePalindrome;
                }
            }
        }
        throw null;
    }


    public int primePalindromeTLE(int N) {
        for (int num = N; num < Integer.MAX_VALUE; num++) {
            if (isPrime(num)) {
                if (isPalindrome(String.valueOf(num))) {
                    return num;
                }
            }
        }
        return 2;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
