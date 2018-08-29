package BruteForce;

public class PermutationSequence {

    public static void main(String[] args) {
        PermutationSequence inst = new PermutationSequence();
        inst.getPermutation(5, 37);
    }

    public String getPermutation(int n, int k) {

        StringBuilder fromPermutation = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            fromPermutation.append(String.valueOf(i));
        }

        k--;

        k = k % (getFactorial(n));

        while (k > 0) {
            getNextPermutation(fromPermutation);
            k--;
        }

        return fromPermutation.toString();
    }

    private int getFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private void getNextPermutation(StringBuilder fromPermutation) {

        int len = fromPermutation.length(), i = len - 1;

        while (i > 0) {
            if (fromPermutation.charAt(i - 1) < fromPermutation.charAt(i)) {
                break;
            }
            i--;
        }

        int toSwapIndex = i - 1;

        i = len - 1;
        while (i > toSwapIndex) {
            if (fromPermutation.charAt(i) > fromPermutation.charAt(toSwapIndex)) {
                swap(toSwapIndex, i, fromPermutation);
                break;
            }
            i--;
        }

        reverse(toSwapIndex + 1, len - 1, fromPermutation);
    }

    private void swap(int i, int j, StringBuilder sb) {
        char ch = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, ch);
    }

    private void reverse(int s, int e, StringBuilder sb) {
        while (s < e) {
            swap(s, e, sb);
            s++;
            e--;
        }
    }
}
