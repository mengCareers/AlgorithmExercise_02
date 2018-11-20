package 编程之美;

/**
 * Factorial is an interesting function.
 * Q0: given N, get the position of last one in Binary N!
 * actually count number of 2 in factors
 * Q1: given N, count the number of 0 in the end of N!
 * actually count number of 5 in factors
 */
public class Factorial {
    public int countLastPositionOfOneInBinaryFactorial(int N) {
        if (N == 0) {
            try {
                throw new Exception("Invalid parameter.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int cntTwo = 0, curNum;
        for (int i = 1; i <= N; i++) {
            curNum = i;
            while (curNum % 2 == 0) {
                cntTwo++;
                curNum /= 2;
            }
        }
        return cntTwo;
    }

    public int countEndingZeroesOfFactorial(int N) {
        if (N == 0) {
            try {
                throw new Exception("Invalid parameter.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int cntFive = 0, curNum;
        for (int i = 1; i <= N; i++) { // all factorials
            curNum = i;
            while (curNum % 5 == 0) {
                cntFive++;
                curNum /= 5;
            }
        }
        return cntFive;
    }

    private static int fact(int num) {
        if (num == 1) {
            return 1;
        }
        return num * fact(num - 1);
    }

}
