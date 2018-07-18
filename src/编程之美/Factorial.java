package 编程之美;

/**
 * Factorial is an interesting function.
 * Q0: given N, get the position of last one in Binary N!
 * Q1: given N, count the number of 0 in the end of N!
 */
/*         M
N! = x * 10
m is the answer
 x   y   z
2 * 3 * 5
x, z matters, M = min(x, z), it is inductive that z is smaller, thus,
M = z
 */
public class Factorial {
    public int countLastPositionOfOneInBinaryFactorial(int N) {
        // N!含有质因数2的个数 + 1
        int cntTwo = 0, curNum;
        for (int i = 1; i <= N; i++) {
            curNum = i;
            while (curNum % 2 == 0) {
                cntTwo++;
                curNum /= 2;
            }
        }
        return cntTwo + 1;
    }

    public int countEndingZeroesOfFactorial(int N) {
        if (N == 0) {
            try {
                throw new Exception("Invalid parameter.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int cntZero = 0, curNum;
        for (int i = 1; i <= N; i++) { // all factorials
            curNum = i;
            while (curNum % 5 == 0) {
                cntZero++;
                curNum /= 5;
            }
        }
        return cntZero;
    }

    public static void main(String[] args) {

        Factorial inst = new Factorial();

        System.out.println(inst.countLastPositionOfOneInBinaryFactorial(8));

//        inst.countEndingZeroesOfFactorial(0);

    }
}
