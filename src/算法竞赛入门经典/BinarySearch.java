package 算法竞赛入门经典;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch inst = new BinarySearch();
        int[] nums = {2, 2, 2, 4};
        int answer = inst.bSUpperBound(nums, 4);
        System.out.println(answer);
    }

    /**
     * ask for upper bound
     *
     * @param nums
     * @param target
     * @return last index of target in nums + 1 || index to insert
     * 第一个大于等于3的数 i
     * 第一个大于3的数  j
     * [i, j-1]
     * 2 3 4 5
         i j
     i == j
     no 3s
     */
    /* 0 2  target = 1  //
     *  [0, 5]
     * 最后一个小于等于TARGET的数 + 1
     *
     *  (第一个大于TARGET的数 或 插入点) in nums must in [0, len]
     *  [lo, hi+1]
     * 1）lo = 0, hi = len-1;
     * 2）mi, nums[mi] <= target // lo = mi + 1; [mi + 1, hi + 1]
     *        nums[mi] > target // hi = mi - 1;  [lo, mi]
     *        // nums[mi] = target // lo = mi + 1;
     * 3) lo = hi+1; return lo;
     *    lo - 1 = hi lo > hi
     *
     * */
    public int bSUpperBound(int[] nums, int target) {

        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] <= target) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

//        int lo = 0, hi = nums.length;
//        while (lo < hi) {
//            int mi = lo + (hi - lo) / 2;
//            if (nums[mi] < target) {
//                lo = mi + 1;
//            } else if (nums[mi] > target) {
//                hi = mi;
//            } else {
//                if (mi + 1 < nums.length && nums[mi + 1] == target) {
//                    // mi is not the answer
//                    lo = mi + 1;
//                    continue;
//                }
//                return mi;
//            }
//        }
        return lo;
    }

    /**
     * ask for lower bound
     *
     * @param nums
     * @param target
     * @return first index of target in nums || index to insert
     * 第一个大于等于target的位置
     */
    /*
    （第一个大于等于TARGET的数 或 插入点）的范围是 [0, len]

    define [lo, hi + 1]
    1) lo = 0, hi = len - 1
    2) mi
        nums[mi] < target, lo = mi + 1, [mi + 1, hi + 1]
        nums[mi] > target, // hi + 1 = mi, hi = mi - 1 [lo, mi]
        nums[mi] = target, hi + 1 = mi, [lo, mi]
    3) lo = hi + 1

    0 1 1 3 4 target = 1
     */
    public int bSLowerBound(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] < target) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

        return lo;
    }


    // 元素空间没有重复值
    // we are maintaining lo all the time
    public int bSStandardBothInclusive(int[] nums, int target) {
        //lo = 0, hi = len - 1
        // 我们要返回的值始终在[lo, hi]里面
        // [lo, hi]

        //1).[0, len -1] => (0 .. 3 ... 38 ..49)
        //2).[lo, hi] =>
        // nums[mi] > target => hi = mi -1 [lo, mi-1]
        // nums[lo] ... nums[mi-1] (nums[mi] ......
        // nums[mi] < target => lo = mi + 1
        // ... nums[mi]) nums[mi+1] ... nums[hi]
        //3). lo == hi, [5, 3]


        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) { // there is still search space
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] == target) {
                return mi;
            } else if (nums[mi] > target) {
                hi = mi - 1;
            } else { // lo move forward only if num[mi] < target, never move back, until reach the target
                lo = mi + 1;
            }
        }


        throw null;

    }
}
