package CompanyOriented.Pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You have 4 cards each containing a number from 1 to 9.
You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 */
/*
e.g. 4 1 8 7

4  *, /, +, - 1
   *, /, +, - 8
   *, /, +, - 7

func
for any num
    append *, /, +, -
        dfs
3 / 6 * 2
48 / ( 1 * 2) = 48 / 2
48 2
48 / 2 = 24
(5 + 7) * 2 * 1
 */
public class Game24 {


    public static void main(String[] args) {
        Game24 inst = new Game24();

        int[] nums = {7, 1, 2, 5};

        System.out.println(inst.judgePoint24(nums));
    }

    private static final double DIFF_ACCEPTABLE = 0.1;


    public boolean judgePoint24(int[] nums) {

        List<Double> numlist = new ArrayList<>();
        for (int num : nums)
            numlist.add((double) (num));

//        return backtrack_w_parenthesis(numlist);
        return backtrack_wo_parenthesis(numlist, false);
    }


    private boolean backtrack_wo_parenthesis(List<Double> numlist, boolean isLowerPriority) {

        if (numlist.size() == 1) {
            if (Math.abs(numlist.get(0) - 24) <= DIFF_ACCEPTABLE) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < numlist.size(); i++) {
            for (int j = 0; j < i; j++) {

                double num1 = numlist.get(i), num2 = numlist.get(j);

                List<Double> resList = new ArrayList<>();
                resList.addAll(Arrays.asList(num1 + num2, num1 - num2, num2 - num1, num1 * num2));
                if (Math.abs(num1) >= DIFF_ACCEPTABLE)
                    resList.add(num2 / num1);
                if (Math.abs(num2) >= DIFF_ACCEPTABLE)
                    resList.add(num1 / num2);

                numlist.remove(i);
                numlist.remove(j);

                int k = 0;

                for (; k < 3; k++) {
                    numlist.add(resList.get(k));
                    if (backtrack_wo_parenthesis(numlist, true)) {
                        return true;
                    }
                    numlist.remove(numlist.size() - 1);
                }
                if (!isLowerPriority) {
                    for (; k < resList.size(); k++) {
                        numlist.add(resList.get(k));
                        if (backtrack_wo_parenthesis(numlist, false)) {
                            return true;
                        }
                        numlist.remove(numlist.size() - 1);
                    }
                }

                numlist.add(j, num2);
                numlist.add(i, num1);
            }
        }

        return false;
    }

    private boolean backtrack_w_parenthesis(List<Double> numlist) {

        if (numlist.size() == 1) {
            if (Math.abs(numlist.get(0) - 24) <= DIFF_ACCEPTABLE) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < numlist.size(); i++) {
            for (int j = 0; j < i; j++) {

                double num1 = numlist.get(i), num2 = numlist.get(j);

                List<Double> resList = new ArrayList<>();
                resList.addAll(Arrays.asList(num1 + num2, num1 - num2, num2 - num1, num1 * num2));
                if (Math.abs(num1) >= DIFF_ACCEPTABLE)
                    resList.add(num2 / num1);
                if (Math.abs(num2) >= DIFF_ACCEPTABLE)
                    resList.add(num1 / num2);

                numlist.remove(i);
                numlist.remove(j);

                for (double res : resList) {
                    numlist.add(res);
                    if (backtrack_w_parenthesis(numlist))
                        return true;
                    numlist.remove(numlist.size() - 1);
                }

                numlist.add(j, num2);
                numlist.add(i, num1);
            }
        }

        return false;
    }
}
