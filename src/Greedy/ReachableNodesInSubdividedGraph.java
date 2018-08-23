package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
*
* */
public class ReachableNodesInSubdividedGraph {
    public int reachableNodes(int[][] edges, int M, int N) {

        int[][] graph = new int[N][N];
        for (int[] tmp : graph) {
            Arrays.fill(tmp, -1);
        }

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }


        PriorityQueue<int[]> pq_node_movesremained = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        pq_node_movesremained.add(new int[]{0, M});

        boolean[] visited = new boolean[N];

        int result = 0;

        while (!pq_node_movesremained.isEmpty()) {
            int[] cur = pq_node_movesremained.poll();
            int node = cur[0];
            int moves = cur[1];
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            result++;
            for (int nei = 0; nei < N; nei++) {
                if (graph[node][nei] != -1) {
                    if (!visited[nei] && moves >= graph[node][nei] + 1) {
                        pq_node_movesremained.add(new int[]{nei, moves - graph[node][nei] - 1});
                    }
                    int movesCost = Math.min(moves, graph[node][nei]);
                    graph[nei][node] -= movesCost;
                    result += movesCost;
                }
            }
        }

        return result;
    }
}

