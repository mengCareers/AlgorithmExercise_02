package 算法竞赛入门经典.DFS;

import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSortImpl {

    public void topologicalSort(LinkedList<Integer>[] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topologicalSortFrom(graph, visited, i, stack);
            }
        }

        while (!stack.isEmpty())
            System.out.print(stack.pop());
    }

    private void topologicalSortFrom(LinkedList<Integer>[] graph, boolean[] visited, int i, Stack stack) {

        visited[i] = true;

        for (int neighbourNode : graph[i]) {
            if (!visited[neighbourNode]) {
                topologicalSortFrom(graph, visited, neighbourNode, stack);
            }
        }
        stack.push(i);
    }
}
