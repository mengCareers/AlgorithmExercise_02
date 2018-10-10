package CompanyOriented.Google;

/**
 * input: n
 * output:last remaining number
 * 1 2 3 4 5 6 7 8 9
 * 2   4   6   8
 * 2       6
 * 6
 * We simply start from the left,
 * # USING ARRAY WILL EASILY GET MLE
 */
public class EliminationGame {

    public int lastRemaining(int n) {
        int head = 1, remaining = n, step = 1;
        boolean leftToRight = true;
        while (remaining > 1) {
            if (leftToRight || remaining % 2 == 1) {
                head += step;
            }
            step *= 2;
            remaining /= 2;
            leftToRight = !leftToRight;
        }
        return head;
    }

    public static void main(String[] args) {
        EliminationGame inst = new EliminationGame();
        for (int i = 4; i < 100; i++)
            System.out.println(i + "  " + inst.lastRemaining(i));
    }

    public int lastRemainingTLE(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        return lastRemainingFrom(nums, true);
    }

    private int lastRemainingFrom(int[] nums, boolean isClockwise) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] newNums = new int[nums.length / 2];
        if (isClockwise) {
            int ni = 0;
            for (int i = 1; i < nums.length; i = i + 2) {
                newNums[ni++] = nums[i];
            }
        } else {
            int ni = newNums.length - 1;
            for (int i = nums.length - 2; i >= 0; i = i - 2) {
                newNums[ni--] = nums[i];
            }
        }
        return lastRemainingFrom(newNums, !isClockwise);
    }
}
