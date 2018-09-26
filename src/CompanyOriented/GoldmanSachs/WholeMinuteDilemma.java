package CompanyOriented.GoldmanSachs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
input: array
output: number of pairs of two numbers sum up to 60 * n (n >= 1)
e.g. 10, 50, 50, 10
10 2
50 2
output: 2
 */
/*
TP: same element used twice, BS not applicable

 */
public class WholeMinuteDilemma {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String numsStr = scanner.nextLine();
            if (numsStr.equals("N")) {
                break;
            }
            String[] numStr = numsStr.split(" ");
            int[] nums = new int[numStr.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.valueOf(numStr[i]);
            }
            System.out.println(getPairs(nums));
        }
    }

    public static int getPairs(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 60) {
                nums[i] %= 60;
            }
        }

        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int num : nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }

        int pairNum, twiceCntPairs = 0;
        for (int num : nums) {
            pairNum = 60 - num;
            if (numToCount.containsKey(pairNum)) {
                twiceCntPairs += numToCount.get(pairNum);
            }
            if (pairNum == num) {
                twiceCntPairs--;
            }
        }

        return twiceCntPairs / 2;
    }
}