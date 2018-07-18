package Amazon;

import java.util.*;

/**
 * richer[i] = [x, y] as x has more money than y
 * quiet[x] = q if x has quietness q
 * output: answer[]
 * answer[x] = y if y is the least quiet person among all people who is richer than x
 * for each x, all the people richer than x (if richer[i][1] == x, richer[i][0] add to list)
 * for each y list, the one with smallest quiet value is the answer
 */
public class LoudAndRich {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, List<Integer>> xToRichers = buildGraph(richer);
        int N = quiet.length;
        int[] result = new int[N];
        /*
        DFS
        start point: each one
        end point: all the way down, keep track of least quietness
         */
        for (int i = 0; i < N; i++) {
            result[i] = loudAndRichFrom(i, xToRichers, new HashSet<>(), quiet);
        }

        return result;
    }

    private int loudAndRichFrom(int start, Map<Integer, List<Integer>> xToRichers, Set<Integer> visited, int[] quiet) {

        int smallestQuietValue = Integer.MAX_VALUE, manWithSmallestQuietValue = -1;
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        visited.add(start);
        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (quiet[x] < smallestQuietValue) {
                smallestQuietValue = Math.min(smallestQuietValue, quiet[x]);
                manWithSmallestQuietValue = x;
            }
            if (xToRichers.containsKey(x)) {
                for (int rich : xToRichers.get(x)) {
                    if (!visited.contains(rich)) {
                        stack.push(rich);
                        visited.add(rich);
                    }
                }
            }
        }

        return manWithSmallestQuietValue;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] richer) {
        Map<Integer, List<Integer>> xToRichers = new HashMap<>();
        int x = 0, rich = 0;
        for (int[] pair : richer) {
            x = pair[1];
            rich = pair[0];
            if (!xToRichers.containsKey(x)) {
                xToRichers.put(x, new LinkedList<>());
            }
            xToRichers.get(x).add(rich);
        }
        return xToRichers;
    }

}
