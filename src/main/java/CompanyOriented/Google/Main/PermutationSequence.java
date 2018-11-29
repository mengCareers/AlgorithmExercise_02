package CompanyOriented.Google;

import java.util.LinkedList;
import java.util.List;

/*
input: n, k
output: kth permutation of {1.. n}

It is intuitive to list all permutations, sort them, and get the kth.
However, that may cause TLE.

Let's try dividing the problem in the top-down way,
e.g. n = 4, set = {1, 2, 3, 4}
permu(1,2,3,4) = 1 + permu(2,3,4) = 12 + permu(3,4) = 123 + permu(4)
               = 2 + permu(1,3,4) = 12 + permu(3,4) = 123 + permu(4)







There are 4 x 3 x 2 x 1 = 24 permutations in total
If we are given k = 13,
13 / (3 x 2 x 1) = 2,
               13 / 6 = 2,
 */
public class PermutationSequence {

    public String getPermutation(int n, int k) {

        List<Integer> nums = new LinkedList<>();
        int[] factorial = buildFactorial(n, nums);
        int branchSize = 0, branchIndex = 0;
        StringBuilder result = new StringBuilder();
        k--;

        for (int i = 1; i <= n; i++) {
            branchSize = factorial[nums.size()] / nums.size();
            branchIndex = k / branchSize;
            result.append(nums.get(branchIndex));
            nums.remove(branchIndex);
            k = k % branchSize;
        }
        return result.toString();
    }

    /*
       build the factorial[] and list nums
     */
    private int[] buildFactorial(int n, List<Integer> nums) { // x 1 2 6 24 ..

        int[] factorial = new int[n + 1];
        factorial[0] = 1; // meaningless
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            nums.add(i);
        }
        return factorial;
    }
}
