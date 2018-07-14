package Amazon;

import java.util.*;

/**
 * without duplicates
 */
/*
equally likely, about equal possibility, we learned about random()
instead of shuffling, we try using getrandom
 */
public class ShuffleAnArray {

    List<Integer> aux;
    int[] oriNums;
    int[] nums;
    Random rand;


    public ShuffleAnArray(int[] nums) {
        aux = new LinkedList<>();
        oriNums = Arrays.copyOf(nums, nums.length);
        resetAux();
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return oriNums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        rand = new Random();
        int pickIndex = 0;
        for (int i = 0; i < nums.length; i++) { // 3 2 1
            pickIndex = rand.nextInt(aux.size());
            nums[i] = aux.get(pickIndex);
            aux.remove(pickIndex);
        }
        resetAux();
        return nums;
    }

    private void resetAux() {
        aux.clear();
        for (int num : oriNums) {
            aux.add(num);
        }
    }
}
