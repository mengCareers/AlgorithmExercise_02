package CompanyOriented.Google;

import java.util.*;

/**
 We can't tell which nodes can be roots in the beginning, however, we can tell the leaf nodes for the number of edges connecting to them must be 1.
 We build the undirected graph first with the help of outNodes and inDegree.
 Like BFS implementation of Topological Sort, we start with those leaf nodes, update the number of edges connecting to its adjacent nodes, and remove those leaf nodes. We keep removing the node whoes indegree is 1.
 We update roots in each level, the last one remained will be the result list. Please note that java.util.ConcurrentModificationException will occur if we use list.remove() during the iteration. Instead, we use iterator.remove();. In fact, we don't really need to remove those 'leaf nodes'. However, to make the code clear, I still do the removal in the code as below:
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0 || edges == null || edges.length == 0) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }
        Map<Integer, List<Integer>> outNodes = new HashMap<>();
        int[] inDegree = new int[n];
        buildGraph(n, edges, outNodes, inDegree);
        return topologicalSort(outNodes, inDegree);
    }

    private void buildGraph(int n, int[][] edges, Map<Integer, List<Integer>> outNodes, int[] inDegree) {
        for (int i = 0; i < n; i++) {
            outNodes.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            outNodes.get(edge[0]).add(edge[1]);
            inDegree[edge[0]]++;
            outNodes.get(edge[1]).add(edge[0]);
            inDegree[edge[1]]++;
        }
    }

    private List<Integer> topologicalSort(Map<Integer, List<Integer>> outNodes, int[] inDegree) {
        List<Integer> roots = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 1) {
                queue.add(i);
            }
        }

        Iterator<Integer> iterator;
        while (!queue.isEmpty()) {
            int size = queue.size();
            roots = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                roots.add(node);
                iterator = outNodes.get(node).iterator();
                while (iterator.hasNext()) {
                    int adjNode = iterator.next();
                    inDegree[adjNode]--;
                    iterator.remove();
                    if (inDegree[adjNode] == 1) {
                        queue.add(adjNode);
                    }
                }
            }
        }
        return roots;
    }

}
