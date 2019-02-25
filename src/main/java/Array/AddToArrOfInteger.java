package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AddToArrOfInteger {
    public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> reverseK = toReverseLIST(K);
        System.out.println("reverseK " + reverseK);
        List<Integer> reverseA = new ArrayList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            reverseA.add(A[i]);
        }
        System.out.println("reverseA " + reverseA);
        List<Integer> result = new LinkedList<>();
        int i = 0, j = 0, szA = reverseA.size(), szK = reverseK.size(), carry = 0;

        while (i < szA && j < szK) {
            int digits = reverseA.get(i++) + reverseK.get(j++) + carry;
            result.add(digits % 10);
            carry = digits / 10;
        }

        while (i < szA) {
            int digits = reverseA.get(i++) + carry;
            result.add(digits % 10);
            carry = digits / 10;
        }

        while (j < szK) {
            int digits = reverseK.get(j++) + carry;
            result.add(digits % 10);
            carry = digits / 10;
        }

        if (carry != 0) result.add(carry);
        Collections.reverse(result);
        return result;
    }

    private static List<Integer> toReverseLIST(int K) {
        List<Integer> list = new LinkedList<>();
        while (K / 10 != 0) {
            list.add(K % 10);
            K /= 10;
        }
        if (K % 10 != 0) list.add(K % 10);
        return list;
    }

    public static List<Integer> addToArrayForm2(int[] A, int K) {
        // A to integer
        int num = 0;
        for (int a : A) {
            num = num * 10 + a;
        }

        // add k
        int res = num + K;
        System.out.println(res);
        // to list<integer>
        List<Integer> result = new ArrayList<>();
        while (res != 0) { // 1 2 3 4
            result.add(0, res % 10);
            res /= 10;
        }
        if (res % 10 != 0) result.add(0, res % 10);

        return result;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 5};
        System.out.println(addToArrayForm(A, 806));
    }
}
