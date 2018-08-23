package 剑指offer;

/**
 * input: n
 * output: appearance of 1 in 1..n representation
 */
public class oneIn1toN {

    public static void main(String[] args) {

        oneIn1toN inst = new oneIn1toN();
        int n = 21345;
        int answer = inst.numOfOne(n);
        int answer_bf = inst.numOfOne_BF(n);
        System.out.println(answer);
        System.out.println(answer_bf);
    }

    public int numOfOne(int n) {

        return numOfOneFrom(String.valueOf(n));
    }

    private int numOfOneFrom(String n) {

        if (n.isEmpty()) {
            return 0;
        }

        int numFirstDigitOne = 0;
        int firstDigit = n.charAt(0) - '0';
        if (firstDigit > 1) {
            numFirstDigitOne = (int) (Math.pow(10, n.length() - 1));
        } else if (firstDigit == 1) {
            numFirstDigitOne = Integer.valueOf(n.substring(1)) + 1;
        }

        int numOtherDigitsOne = (int) (firstDigit * (n.length() - 1) * Math.pow(10, n.length() - 2));

        int numRecursiveOne = numOfOneFrom(n.substring(1));

        return numFirstDigitOne + numOtherDigitsOne + numRecursiveOne;
    }

    public int numOfOne_BF(int n) {

        int countOfOne = 0;
        for (int i = 1; i <= n; i++) {
            countOfOne += numOfOne_BFUtil(i);
        }

        return countOfOne;
    }

    private int numOfOne_BFUtil(int n) {
        int countOfOne = 0;
        while (n > 0) {
            if (n % 10 == 1) {
                countOfOne++;
            }
            n /= 10;
        }
        return countOfOne;
    }
}
