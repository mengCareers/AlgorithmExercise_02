package CompanyOriented.Google.Extension;

import java.util.ArrayList;
import java.util.List;

/*
there are 2^n subsets of a set S
2^n binary numbers that have exactly n bits
for simplicity,
    w'll use string notation rather than set notation to encode subsets
 */
public class GenerateSubsetsUsingBinary {

    public static void main(String[] args) {

        GenerateSubsetsUsingBinary inst = new GenerateSubsetsUsingBinary();
        int[] nums = {1, 2, 3};
        System.out.println(inst.subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int binary = (int) Math.pow(2, n);
        List<Integer> tmp;
        for (int cur = 0; cur < binary; cur++) {
            tmp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (((cur >> i) & 1) == 1) {
                    tmp.add(nums[i]);
                }
            }
            result.add(new ArrayList<>(tmp));
        }

        return result;
    }
}
