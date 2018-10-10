package CompanyOriented.Google;

import java.util.Stack;

/**
 e.g. "DDI"

 D D I
 1 2 3 4
 3 2 1 4
 We loop through the [1, 2, ... s.length() + 1],
 and keep pushing the current number to the stack. Whenever we meet 'I', we need to pop all numbers out of the stack to the result array.
 */
public class FindPermutation {
    public static void main(String[] args) {
        FindPermutation inst = new FindPermutation();
        int[] result = inst.findPermutation("DDIIIID");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public int[] findPermutation(String s) {
        if (s == null) {
            return null;
        }
        int[] result = new int[s.length() + 1];
        if (s.length() == 0) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        char[] sArr = s.toCharArray();
        int ri = 0;

        for (int i = 0; i <= sArr.length; i++) {
            stack.push(i + 1);
            if (i == sArr.length || sArr[i] == 'I') {
                while (!stack.isEmpty()) {
                    result[ri++] = stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            result[ri++] = stack.pop();
        }
        return result;
    }
}
