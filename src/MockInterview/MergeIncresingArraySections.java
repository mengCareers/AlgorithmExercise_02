package MockInterview;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeIncresingArraySections {

    public static void main(String[] args) {
        double[] nums = {2.3, 5, 8.4, 3, 5, 1, 3, 9};
        System.out.println(sort(nums));
    }

    public static List<Double> sort(double[] nums) {

        // corner case to add

        List<Double> result = new ArrayList<>();
        PriorityQueue<Integer> pointers = new PriorityQueue<Integer>((a, b) -> (Double.compare(nums[a], nums[b])));
        pointers.add(0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                pointers.add(i);
            }
        }
        boolean[] visited = new boolean[nums.length]; // if nums are too much, waste sapce there, we can save startPoint, class Interval
        while (!pointers.isEmpty()) {
            int pointer = pointers.poll();
            visited[pointer] = true;
            result.add(nums[pointer]);
            if (!pointers.contains(pointer + 1) && (pointer + 1 < nums.length) && !visited[pointer + 1]) {
                pointers.add(pointer + 1);
            }
        }

        return result;
    }

}
