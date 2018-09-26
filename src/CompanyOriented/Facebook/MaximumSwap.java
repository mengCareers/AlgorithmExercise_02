package CompanyOriented.Facebook;

import java.util.*;

/**
 * input: num
 * swap two digits at most once to get the maximum valued number
 * output: the maximum valued number
 * e.g. 1425
 * 5421 is
 * can we always swap the maximum number with the first? yes
 * what if multiple maximum, e.g. 3131? swap the last one with the first < maximum
 * what if 3312? if non-maximum is on the right of above, swap the snd largest..
 */
public class MaximumSwap {
    public static void main(String[] args) {
        MaximumSwap inst = new MaximumSwap();
        int answer = inst.maximumSwap(3231);
        System.out.println(answer);
    }

    public int maximumSwap(int num) { // 3231
        char[] digits = String.valueOf(num).toCharArray();
        Map<Character, Integer> valueToLastOccurrence = new HashMap<>();

        for (int i = 0; i < digits.length; i++) {
            valueToLastOccurrence.put(digits[i], i);
        }

        char[] sortedDigits = Arrays.copyOf(digits, digits.length);
        Arrays.sort(sortedDigits);
        for (int j = sortedDigits.length - 1; j >= 0; j--) {
            if (j != sortedDigits.length - 1 && sortedDigits[j] == sortedDigits[j + 1]) {
                continue;
            }
            char maximum = sortedDigits[j];
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] < maximum) {
                    if (i < valueToLastOccurrence.get(maximum)) {
                        swap(digits, i, valueToLastOccurrence.get(maximum));
                        return Integer.valueOf(new String(digits));
                    } else {
                        break;
                    }
                }
            }
        }
        return num;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
