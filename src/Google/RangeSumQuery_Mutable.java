package Google;

/**
 * sum of elements between indices i and j (i <= j)
 * update(i, val) modifies nums by updating the element at index i to val
 * Binary Indexed Tree
 * is an array actually,
 * e[4] index 4 => 100(2), '1' is the rightmost index 2(2) => 4
 * e[4] = a[4] + a[3] + a[2] + a[1]
 * e[i], which represents range sum to nums[i] (not promise to start at 0, so we need to figure it out)
 */
public class RangeSumQuery_Mutable {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        RangeSumQuery_Mutable inst = new RangeSumQuery_Mutable(nums);
        inst.sumRange(0, 2);
    }

    int[] e;
    int[] nums;

    public RangeSumQuery_Mutable(int[] nums) {
        e = new int[nums.length + 1];
        this.nums = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            update(i, nums[i]);
    }

    public void update(int i, int val) {
        int updateVal = val - nums[i];
        nums[i] = val;
        i++;
        while (i < e.length) {
            e[i] += updateVal;
            i += getRightmostOnePosition(i);
        }
    }

    public int sumRange(int i, int j) {
        return sumRangeFromStart(j) - sumRangeFromStart(i - 1);
    }

    private int sumRangeFromStart(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += e[i];
            i -= getRightmostOnePosition(i);
        }
        return sum;
    }

    private int getRightmostOnePosition(int i) {
        return i & ((~i) + 1);
    }
}
