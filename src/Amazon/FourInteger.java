package Amazon;

import java.util.Arrays;

/*
Given four integers, make F(S) = abs(S[0]-S[1])+abs(S[1]-S[2])+abs(S[2]-S[3]) to be largest
 */
public class FourInteger {
    public int[] Solution(int A, int B, int C, int D) {
        int[] numbers = {A, B, C, D};
        Arrays.sort(numbers);
        swap(numbers, 0, 1);
        swap(numbers, 2, 3);
        swap(numbers, 1, 3);
        return numbers;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
