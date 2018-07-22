package 编程之美;

public class FindNearestPoints {


    // look at one-dimension first, N numbers find two with the minimum difference
    // divide into Left, Right, the smallest diff comes from left or right or Across
    private int[] findNearestNumbers(int[] nums) {

        int num1 = 0, num2 = 0, minDiff = Integer.MAX_VALUE;
        int s = 0, e = nums.length - 1;
        while (s < e) {

        }

        return null;
    }

    private void findMinDif(int s, int e, int[] nums) {
        while (s < e) {
            int k = s + (e - s) / 2; // [s, k) (k, e]
            findMinDif(s, k, nums);
            findMinDif(k, e, nums);
        }
    }
}




