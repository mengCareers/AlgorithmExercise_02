package 算法竞赛入门经典.DFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HamiltonianCycle {

    public static void main(String[] args) {
        HamiltonianCycle inst = new HamiltonianCycle();
        int[][] graph = new int[3][3];
        graph[0][1] = 1;
        graph[1][0] = 1;
        inst.hasHamiltonianCycle(graph);
    }

    /*
    check if a graph contains Hamiltonian Cycle
     */
    public boolean hasHamiltonianCycle(int[][] graph) {

        // assume we start at vertex 0
        return hasHamiltonianCycleFrom(graph, 0, new HashSet<>());
    }


    /*
    check if the rest graph starting at vertex contains Hamiltonian Cycle
     */
    public boolean hasHamiltonianCycleFrom(int[][] graph, int vertex, Set<Integer> visited) {

        if (visited.size() == graph.length) {
            return (graph[0][vertex] == 1);
        }

        visited.add(vertex);

        for (int neighborVertex = 0; neighborVertex < graph[vertex].length; neighborVertex++) {
            if (graph[vertex][neighborVertex] == 1 && !visited.contains(neighborVertex)) {
                if (!visited.contains(neighborVertex)) {
                    if (hasHamiltonianCycleFrom(graph, neighborVertex, visited)) {
                        return true;
                    }
                    visited.remove(neighborVertex);
                }
            }
        }
        return false;
    }

}
