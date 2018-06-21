package Google;

import java.util.Stack;

/**
 * input: temperatures[]
 * output: distance from next bigger OR 0
 * 73 74 75 71 69 72 76 73
 * 1  1  4  2  1  1  0  0
 * | 75 71 69 72 76
 * 1 1 5
 * save indices
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures inst = new DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = inst.dailyTemperatures(temperatures);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return temperatures;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] result = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }
}
