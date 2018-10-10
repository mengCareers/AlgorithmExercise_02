package CompanyOriented.Pinterest;

/*
Given sorted int[] nums, find the element appears bigger than n / 4 times for n is the number of elements in total.
Return -1 if no majority exists.
follow-up n/k
 */
public class MajorityInSorted {

    public static void main(String[] args) {
        int[] nums = {3, 3, 3, 4, 4, 4, 4};
//        System.out.println(findMajorityInSorted_4(nums));
        System.out.println(findMajorityInSorted_k(nums, 0)); // -1
        System.out.println(findMajorityInSorted_k(nums, 1)); // -1
        System.out.println(findMajorityInSorted_k(nums, 2)); // -1
        System.out.println(findMajorityInSorted_k(nums, 3)); // 7
        System.out.println(findMajorityInSorted_k(nums, 4)); // 7
    }

    public static int findMajorityInSorted_k(int[] nums, int k) {
        if (k <= 1)
            return -1;
        int n = nums.length, cnt = n / k;
        for (int i = cnt - 1; i < n; i += cnt) {
            if (binarySearch(nums[i], nums, false) - binarySearch(nums[i], nums, true) > cnt)
                return nums[i];
        }
        return -1;
    }

    public static int findMajorityInSorted_4(int[] nums) {
        int n = nums.length, cnt = n / 4;
        for (int i = 2; i < n; i += 3) {
            if (binarySearch(nums[i], nums, false) - binarySearch(nums[i], nums, true) > cnt)
                return nums[i];
        }
        return -1;
    }

    private static int binarySearch(int target, int[] nums, boolean canEqualTo) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            if (nums[lo] < target) {
                lo++;
            } else if (nums[lo] > target) {
                hi--;
            } else {
                if (canEqualTo)
                    hi--;
                else
                    lo++;
            }
        }
        return lo;
    }
}
