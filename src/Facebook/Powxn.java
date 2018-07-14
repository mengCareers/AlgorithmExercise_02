package Facebook;

/**
 * Implement pow(x, n),
 * which calculates x raised to the power n (xn)
 */
public class Powxn {
    /*
     * recursion, 3 ^ 5 = 3 ^ 4 * 3
     *            3 ^ 4 = 3 ^ 3 * 3
     *            x ^ n = x ^ (n - 1) * x
     * base case: n == 0, return 1
     * what if n < 0,
     *           3 ^ (-2) = 1 / (3 ^ 2)
     * */
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n % 2 == 0)
            return myPow(x * x, n / 2);
        return n > 0 ? x * myPow(x * x, n / 2) : 1 / (x * myPow(x * x, -n / 2));
    }

    public static void main(String[] args) {
        Powxn inst = new Powxn();
        double answer = inst.myPow(0.000001, 100);
        System.out.println(answer);
    }
}
