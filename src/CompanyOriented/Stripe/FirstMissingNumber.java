package CompanyOriented.Stripe;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;

/*
positive int, return 1st missing one (starting from 1)
Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.


 */
/*
for 1... check in nums, like two pointers

Time complexity: O(nlogn + n) which is O(nlogn)
 */
public class FirstMissingNumber {

    /*
    We use array elements as index.
    To mark presence of an element x, we change the value at the index x to negative.
    return first index value at which is positive
     */
    // Mark arr[i] as visited by making arr[arr[i] - 1] negative.

    public static int getFirstMissingNumber_On(int[] nums) { // 3 1 -5
        int start = segregate(nums), j = 0; // -1 -2 -3 1
        int[] positiveNums = new int[nums.length - start];
        for (int i = start; i < nums.length; i++) {
            positiveNums[j++] = nums[i];
        }

        for (int i = 0; i < positiveNums.length; i++) {
            if (Math.abs(positiveNums[i]) - 1 < positiveNums.length && positiveNums[Math.abs(positiveNums[i]) - 1] > 0) {
                positiveNums[Math.abs(positiveNums[i]) - 1] = -positiveNums[Math.abs(positiveNums[i]) - 1];
            }
        }
        int i = 0;
        for (; i < positiveNums.length; i++) {
            if (positiveNums[i] > 0)
                return i + 1;
        }
        return i + 1;
    }

    private static int segregate(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        return j;
    }

    public static int getFirstMissingNumber_On_On(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        int i = 1;
        while (true) {
            if (!set.contains(i))
                return i;
            i++;
        }
    }

    public static int getFirstMissingNumber_Onlogn(int[] nums) {
        int target = 1;
        Arrays.sort(nums);
        int i = 0;
        for (; i < nums.length; i++) { // Nums should be sorted.
            if (nums[i] != target) {
                return target;
            }
            target++;
        }
        return nums[--i] + 1;
    }

    private static Map<String, Set<Integer>> map = new HashMap<>();

    public String allocate(String serverType) {
        map.putIfAbsent(serverType, new HashSet<>());
        Set<Integer> numberSet = map.get(serverType);
        int[] arr = new int[numberSet.size()];
        int i = 0;
        for (int number : numberSet)
            arr[i++] = number;
        int num = getFirstMissingNumber_On(arr);
        map.get(serverType).add(num);
        return serverType + num;
    }

    public void deallocate(String serverType, int num) { // Needs to delete randomly.
        if (!map.containsKey(serverType))
            return;
        map.get(serverType).remove((Integer) num);
    }

    public static void main(String[] args) {

        FirstMissingNumber instance = new FirstMissingNumber();

        System.out.println(instance.allocate("apibox"));
        System.out.println(instance.allocate("apibox"));
        instance.deallocate("apibox", 1);
        System.out.println(instance.allocate("apibox"));
        System.out.println(instance.allocate("sitebox"));

    }
}
