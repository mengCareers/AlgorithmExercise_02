package CompanyOriented.GoldmanSachs;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

/*
input: nums
output: the change we can get max(nums[i] + nums[j])
保留两位数且不四舍五入。
 */
public class FootballGame {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String numsStr = scanner.nextLine();
//            if (numsStr.equals("N")) {
//                break;
//            }
//            String[] numStr = numsStr.split(" ");
//            int[] nums = new int[numStr.length];
//            for (int i = 0; i < nums.length; i++) {
//                nums[i] = Integer.valueOf(numStr[i]);
//            }
//            System.out.println(String.valueOf(getChance(nums)));
//        }
    }

    public static double getChance(int[] nums) {

        int cntMaxSum = 0, cntMax = 0, n = nums.length;
        Arrays.sort(nums);
        int max = nums[n - 1], sndMax = nums[n - 2];

        if (max != sndMax) {
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] == sndMax) {
                    cntMaxSum++;
                }
            }
        } else {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == max) {
                    cntMax++;
                } else {
                    break;
                }
            }
            cntMaxSum = cntMax * (cntMax - 1) / 2;
        }

        double result = (double) cntMaxSum / (n * (n - 1) / 2);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);

        return Double.valueOf(decimalFormat.format(result));
    }
}
