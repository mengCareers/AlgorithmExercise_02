package CompanyOriented.Airbnb;

import java.util.*;

/*
 *
 * 2 5
 * 5 4 3 2
 *
 * 2 8 4 10 6
 * sort
 * 10 8 6 4 2
 * 10 * 1 + 9 * 1 + 8 * 2 + 7 * 2 + 6 * 3 + 5 * 3 + 4 * 4 + 3 * 4
 * 10 + 9 + 16 + 14 + 18 + 15 + 16 + 12 = 19 + 30 + 33 + 28 = 49 + 61 = 110
 * s   e
 * 10, 1
 * 8, 1
 * 6, 1
 * 4, 1
 * 2, 1
 *
 * 10 * 1 + 9 * 1 + 8 * 2 + 7 * 2 + 6 * 3
 * */
public class TicketResellers {
    // 3 * 4 + 2 *
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(3);
        a.add(3);
        a.add(3);
        System.out.println(maximumAmount(a, 4));
    }

    public static long maximumAmount(List<Integer> a, long k) {
        long resultAmount = 0;
        TreeMap<Integer, Long> treeMap = new TreeMap(Collections.reverseOrder()); // key : price, value : frequency, sort decreasingly
        for (int tmp : a) {
            treeMap.put(tmp, treeMap.getOrDefault(tmp, 0L) + 1);
        }

        int curPrice = 0; // current ticket price
        long curCnt = 0; // number of tickets with current price initially
        long sumCnt = 0; // number of tickets with current price now

        while (k > 0) {
            curPrice = treeMap.firstKey();
            curCnt = treeMap.remove((Integer) curPrice);
            sumCnt += curCnt;

            while (treeMap.isEmpty() || curPrice > treeMap.firstKey()) {
                if (sumCnt >= k) {
                    return resultAmount + curPrice * k;
                }
                resultAmount += curPrice * sumCnt;
                curPrice--;
                k -= sumCnt;
            }
        }

        return resultAmount;
    }
}
