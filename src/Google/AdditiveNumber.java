package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * True if num is an additive number
 * additive : nums[i] + num[i + 1] = num[i + 2]
 * Start State: curIdx = 0, result.size == 0
 * End State: curIdx = num.length(), result.size() >= 3
 * For each step,
 * the current number curNum starts at curIdx,
 * ends at index from [curIdx, num.length() - 1]
 * we try each possible end, if it is additive with the previous, i.e., result.size() <= 1 || curNum == result.get(result.size() - 1) + result.get(result.size() - 2)
 * and see if it could enable a valid rest for answer
 * We will terminate the recursion when we meet the base case, i.e., the End State
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        return isAdditiveNumberFrom(0, num, new ArrayList<>());
    }

    private boolean isAdditiveNumberFrom(int curIdx, String num, List<String> result) {
        if (curIdx == num.length() && result.size() >= 3) {
            return true;
        }
        for (int i = curIdx; i <= num.length() - 1; i++) {
            if (i != curIdx && num.charAt(curIdx) == '0') {
                break;
            }
            String curNum = num.substring(curIdx, i + 1);
            if (result.size() <= 1 || curNum.equals(
                    String.valueOf(
                            Long.parseLong(result.get(result.size() - 1)) + Long.parseLong(result.get(result.size() - 2)))
            )) {
                result.add(curNum);
                if (isAdditiveNumberFrom(i + 1, num, result)) {
                    return true;
                }
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
}
