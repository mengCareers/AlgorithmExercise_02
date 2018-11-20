package 算法图解;

import java.util.Arrays;

/**
 * Q:find shortest path from source to dest (will get shortest distance to all other vertices as well)
 * graph.length = n, vertices 0 .. n - 1
 * graph[i][j] == 0 if there is no edge between i, j
 * graph[i][j] >  0 if there is an edge between i, j with length graph[i][j]
 * src,dest are vertices, shortest path from src to dest
 */
public class Dijkstra {
    public static void main(String[] args) {
        Dijkstra inst = new Dijkstra();
        int[][] graph = {
                {0, 6, 0, 2},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 3, 5, 0}};
        inst.dijkstra(graph, 0, 2);
    }

    public void dijkstra(int[][] graph, int src, int dest) {

        // corner cases to add
        int n = graph.length;
        int[] distanceFromSrc = new int[n];
        Arrays.fill(distanceFromSrc, Integer.MAX_VALUE);
        distanceFromSrc[src] = 0;
        boolean[] visited = new boolean[n];

        for (int times = 0; times < n; times++) {
            int u = getNodeWithMinDistanceFromSrc(distanceFromSrc, visited); // src intially
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (visited[v] || graph[u][v] == 0 || distanceFromSrc[v] <= distanceFromSrc[u] + graph[u][v]) { // visited[v] = true contains u == v occasion
                    continue;
                }
                distanceFromSrc[v] = distanceFromSrc[u] + graph[u][v];
            }
        }

        System.out.println("node: " + src + " shortest distance to dest: " + dest + " is " + distanceFromSrc[dest]);

    }

    private int getNodeWithMinDistanceFromSrc(int[] distanceFromSrc, boolean[] visited) {

        int nodeWithMinDist = 0, minDist = Integer.MAX_VALUE;

        for (int i = 0; i < distanceFromSrc.length; i++) {
            if (!visited[i] && distanceFromSrc[i] < minDist) {
                minDist = distanceFromSrc[i];
                nodeWithMinDist = i;
            }
        }

        return nodeWithMinDist;
    }
}
