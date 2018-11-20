package MockInterview;

import java.util.*;

public class CanConstructUniqueOrg {
    public static void main(String[] args) {
        int[][] sequence = {{1, 2}, {3, 2}};
        int n = 3;
        System.out.println(canConstructUniqueOrg(sequence, n));
    }

    public static boolean canConstructUniqueOrg(int[][] sequence, int n) {

// corner cases to add

// construct graph
        List<List<Integer>> outList = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            outList.add(new ArrayList<>());
        int[] inDegree = new int[n + 1];
        for (int[] seq : sequence) {
            int u = seq[0];
            int v = seq[1];
            outList.get(u).add(v);
            inDegree[v]++;
        }

// topological sort
//        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int v = 1; v <= n; v++) {
            if (inDegree[v] == 0) {
                queue.offer(v);
//                visited.add(v);
            }
        }
        if (queue.size() > 1) {
            return false;
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (queue.size() > 1) {
                return false;
            }
            result.add(node);
            for (int neighbour : outList.get(node)) {
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        return result.size() == n;
    }

}
