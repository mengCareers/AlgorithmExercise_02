package CompanyOriented.Salesforce;

import java.util.TreeSet;

/**
 * input: Set<Integer> elements, String[] operations, int[] x
 * -- operation: push(x), remove(x)
 * output: int[] result
 */
public class ProductMaxMin {

    private static final String PUSH = "push";
    private static final String POP = "pop";

    public int[] getProductMaxMin(String[] operations, int[] x) {
        TreeSet<Integer> set = new TreeSet<>();
        int n = operations.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            String action = operations[i];
            int val = x[i];
            if (action.equals(PUSH)) {
                set.add(val);
            } else if (action.equals(POP)) {
                set.remove(val);
            }
            result[i] = set.first() * set.last();
        }

        return result;
    }
}

/**
 * 0 7 8 6 1 4
 * e.g. 0 5 7 8
 */