package CompanyOriented.Facebook;

import java.util.Arrays;
import java.util.Stack;

/**
 * undirected graph
 * true if it is bipartite, such that every edge in graph has one node in A and another in B
 * <p>
 * MENTOR: Coloring by DFS, Every neighbor gets colored the opposite color from the current node.
 * If we find a neighbor colored the same color as the current node, then our coloring was impossible.
 */
public class IsBipartite {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 - blue, 1 - red, -1 - uncolored
        Arrays.fill(color, -1);
        for (int start = 0; start < n; start++) { // why try each node as start?
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack<>();
                stack.push(start);
                color[start] = 0;
                while (!stack.empty()) {
                    int curNode = stack.pop();
                    for (int neighbourNode : graph[curNode]) {
                        if (color[neighbourNode] == -1) {
                            stack.push(neighbourNode);
                            color[neighbourNode] = color[curNode] ^ 1;
                        } else if (color[neighbourNode] == color[curNode]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
