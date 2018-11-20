package CompanyOriented.Airbnb;

import java.util.List;
import java.util.Stack;

/*
int[] nums, for num, - the first >= itself
2   3   1  2  4  2
-1  -1    -2 -2
1 2 1 0 2 2
5  1 3   4  6  2
-1 0 -2  -2 -2 0
4 1 1 2 4 2 1, 5

1 3  3  2 5
0 -3 -2 0 0
1 0  1  2 5
final = 0
list nonDiscounted
for i 0 .. nums.length-1
    for j i+1 .. nums.length=1
        if (nums[j] <= nums[i]) {
            break;
        }
    }

    if (j == nums.length) {
        nonDiscounted.add(i)
        continue;
    }
    final += nums[i]-nums[j];
 */
public class FinalDiscountedPrice {
    public static void finalPrice(List<Integer> prices) {

        if (prices == null || prices.size() == 0) {
            System.out.println(0);
            return;
        }

        int n = prices.size(); // number of items
        long finalPrices = 0; // total cost of all items
        Stack<Integer> stack = new Stack<>(); // indices of increasing prices
        int[] nonDiscounted; // indices of non-discounted prices

        stack.push(0);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices.get(i) <= prices.get(stack.peek())) {
                finalPrices += prices.get(stack.pop()) - prices.get(i);
            }
            stack.push(i);
        }
        int m = stack.size(); // number of non-discounted items
        nonDiscounted = new int[m];
        for (int j = m - 1; j >= 0; j--) {
            nonDiscounted[j] = stack.pop();
            finalPrices += prices.get(nonDiscounted[j]);
        }
        System.out.println(finalPrices);
        System.out.print(nonDiscounted[0]);
        for (int j = 1; j < m; j++)
            System.out.print(" " + nonDiscounted[j]);
    }
}
