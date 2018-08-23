package 算法竞赛入门经典.Graph;

import java.util.LinkedList;

public class EulerianGraph {
    /*
    return 2 if Eulerian (with Euler Circuit)
    return 1 if Semi-Eulerian (with Euler Path)
    return 0 if not Eulerian
     */
    public int isEulerian(LinkedList<Integer>[] graph) {
        // check if all non-zero degree vertices are connected DFS or disjoint sets
        if (!isConnected(graph)) {
            return -1;
        }

        // count vertices with odd degree
        int countVerticesWithOddDegree = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].size() % 2 == 1) {
                countVerticesWithOddDegree++;
            }
        }

        if (countVerticesWithOddDegree > 2) {
            return 0;
        }
        return (countVerticesWithOddDegree == 2) ? 1 : 2;
    }


    private boolean isConnected(LinkedList<Integer>[] graph) {

        int n = graph.length, i = 0;
        boolean[] visited = new boolean[n];

        for (; i < n; i++) {
            if (!graph[i].isEmpty()) {
                break;
            }
        }
        if (i == n) { // if no edges
            return true;
        }

        isConnectedFrom(visited, graph, i); // make sure not start with 0-degree

        for (i = 0; i < visited.length; i++) {
            if (!visited[i] && !graph[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void isConnectedFrom(boolean[] visited, LinkedList<Integer>[] graph, int vertex) {
        visited[vertex] = true;
        for (int neighbourVertex : graph[vertex]) {
            if (!visited[neighbourVertex]) {
                isConnectedFrom(visited, graph, neighbourVertex);
            }
        }
    }
}
