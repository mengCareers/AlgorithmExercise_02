package Amazon;

/**
 * input: L, R
 * output: count of numbers in [L, R] having a prime number of set bits in BR
 * # of set bits: # of 1s
 */

/*
we can simply loop numbers [L, R]
get # of 1s
and check if it is prime by

 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        for (int num = L; num <= R; num++) {
            if (isPrime(cntSetBitsInBinaryRepresentation(num))) {
                result++;
            }
        }
        return result;
    }

    private int cntSetBitsInBinaryRepresentation(int num) {
        int cntSetBits = 0;
        while (num != 0) {
            cntSetBits += num % 2;
            num >>= 1;
        }
        return cntSetBits;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
