package BruteForce;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {

        // corner cases to add

        List<List<Integer>> result = new ArrayList<>();
        subset(n, k, result, new ArrayList<>(), 1);

        return result;
    }

    private void subset(int n, int k, List<List<Integer>> result, List<Integer> tmp, int start) {

        if (k == 0) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i <= n; i++) {
            tmp.add(i);
            subset(n, k - 1, result, tmp, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
