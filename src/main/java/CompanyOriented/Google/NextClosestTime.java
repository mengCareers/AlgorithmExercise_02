package CompanyOriented.Google;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Thinking Process
 * There are 4 digits, we start from the 1st rightmost digit, which is the least significant digit. To construct a valid closestTime, we try changing it to its nextBigger (the smallest digit whose value is bigger than the 1st rightmost digit). If it does not have nextBigger or the closestTime constructed using nextBigger is not valid, we change it to the minDigit (the smallest digit among the 4 digits) and do with the 2nd rightmost digit following the same pattern...whenever a digit is changed to its nextBigger successfully, we're done. If we are not able to construct a valid closestTime from start to finish, we set all digits to the minDigit, e.g., 23:59 to 22:22.
 * Attention
 * We utilize TreeSet to get nextBigger's.
 * The flag hasFound indicates whether we have constructed a valid cloestTime.
 */
public class NextClosestTime {
    public static void main(String[] args) {
        NextClosestTime inst = new NextClosestTime();
        Scanner scanner = new Scanner(System.in);
        String input, answer;
        while (true) {
            System.out.print("input: ");
            input = scanner.nextLine();
            if (input.equals("N")) {
                break;
            }
            answer = inst.nextClosestTimeNoReuse(input);
            System.out.println(answer);
        }
    }

    public String nextClosestTimeNoReuse(String time) {
        time = time.substring(0, 2) + time.substring(3);
        char[] array = time.toCharArray();
        while (!isValidTime(getNextPermutation(array))) {

        }
        StringBuilder result = new StringBuilder();
        for (char ch : array) {
            result.append(ch);
        }
        result.insert(2, ":");
        return result.toString();
    }

    private boolean isValidTime(char[] array) {
        int h1 = array[0] - '0', h2 = array[1] - '0', m1 = array[2] - '0', m2 = array[3] - '0';
        return ((h1 * 10 + h2 < 24) && (m1 * 10 + m2 < 60));
    }

    public char[] getNextPermutation(char[] array) {
        int pivot = -1;
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] < array[i + 1]) {
                pivot = i;
                for (int j = array.length - 1; j > i; j--) {
                    if (array[j] > array[pivot]) {
                        swap(pivot, j, array);
                        break;
                    }
                }
                break;
            }
        }
        reverseFrom(pivot + 1, array.length - 1, array);
        return array;
    }

    private void reverseFrom(int start, int end, char[] array) {
        while (start < end) {
            swap(start, end, array);
            start++;
            end--;
        }
    }

    private void swap(int i, int j, char[] array) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public String nextClosestTime(String time) {
        String[] hhmm = time.split(":");
        String hh = hhmm[0];
        String mm = hhmm[1];
        time = hh + mm;
        TreeSet<Integer> set = new TreeSet<>();
        int[] closestTime = new int[4];
        int ci = 0;
        int minDigit = 10;
        for (char digit : time.toCharArray()) {
            closestTime[ci++] = digit - '0';
            minDigit = Math.min(minDigit, digit - '0');
            set.add(digit - '0');
        }
        boolean hasFound = false;
        int i = 3;
        while (!hasFound && i >= 0) {
            int curDigit = closestTime[i];
            Integer nextBigger = set.higher(curDigit);
            if (nextBigger != null) {
                closestTime[i] = nextBigger;
                if (isValid(closestTime)) {
                    hasFound = true;
                } else {
                    closestTime[i] = minDigit;
                }
            } else {
                closestTime[i] = minDigit;
            }
            i--;
        }
        StringBuilder result = new StringBuilder();

        for (int t : closestTime) {
            if (!hasFound) {
                result.append(minDigit);
            } else {
                result.append(String.valueOf(t));
            }
            if (result.length() == 2) {
                result.append(":");
            }
        }
        return result.toString();
    }

    /**
     * @param nums
     * @return true if number constructed by nums is valid time representation
     */
    private boolean isValid(int[] nums) {
        int sum1 = nums[0] * 10 + nums[1];
        int sum2 = nums[2] * 10 + nums[3];
        return sum1 < 24 && sum2 < 60;
    }
}
