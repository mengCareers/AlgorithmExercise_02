package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindJudge {
    public int findJudge(int N, int[][] trust) {
        int judge = -1;
        // Build maps.
        Map<Integer, Set<Integer>> trustedMap = new HashMap<>();
        Map<Integer, Set<Integer>> trustMap = new HashMap<>();
        for (int[] trustPair : trust) {
            // u trust v.
            int u = trustPair[0];
            int v = trustPair[1];
            trustedMap.putIfAbsent(v, new HashSet<>());
            trustedMap.get(v).add(u);
            trustMap.putIfAbsent(u, new HashSet<>());
            trustMap.get(u).add(v);
        }
        // Find judge.
        for (int i = 1; i <= N; i++) {
            if (trustedMap.containsKey(i) && trustedMap.get(i).size() == N - 1) {
                if (!trustMap.containsKey(i)) return i;
            }
        }

        return -1;
    }
}
