package CompanyOriented.Amazon;

/**
 * input: N
 * output: largest number that is <= N with monotone increasing digits
 */
/*
list all monotone pick <= N
or list all N test if monotone
 */
public class MonotoneIncreasingDigit {
    public int monotoneIncreasingDigits(int N) {

        char[] arrN = String.valueOf(N).toCharArray();
        int monotoneIncreasingToTheLeftOf = arrN.length - 1;
        for (int i = arrN.length - 1; i > 0; i--) {
            if (arrN[i] < arrN[i - 1]) {
                monotoneIncreasingToTheLeftOf = i - 1;
                arrN[i - 1]--;
            }
        }
        for (int i = monotoneIncreasingToTheLeftOf + 1; i < arrN.length; i++) {
            arrN[i] = '9';
        }

        return Integer.parseInt(new String(arrN));
    }

    public int monotoneIncreasingDigitsTLE(int N) {
        for (int num = N; num >= 0; num--) {
            if (isMonotoneIncreasing(num)) {
                return num;
            }
        }
        return -1;
    }

    private boolean isMonotoneIncreasing(int num) {
        char[] chNum = String.valueOf(num).toCharArray();
        for (int i = 0; i < chNum.length; i++) {
            if ((chNum[i] - '0') > (chNum[i + 1] - '0')) {
                return false;
            }
        }
        return true;
    }
}
