package CompanyOriented.Karat;

import java.util.*;

/*
 1     2   3
/  \  /      \
4   5          6
                 \
                  7
 */
public class DirectedFindParents {
    public static void main(String[] args) {
        DirectedFindParents inst = new DirectedFindParents();
        int[][] edges = {{1, 4}, {1, 5}, {2, 5}, {3, 6}, {6, 7}};
        List<Integer> result = inst.get01ParentNode(edges);
        for (int node : result)
            System.out.print(node + ", ");
        System.out.println();
        System.out.println(inst.hasCommonAncestor(edges, 4, 5));
        System.out.println(inst.hasCommonAncestor(edges, 7, 5));
        System.out.println(inst.getFarthestAncestor(edges, 7));

    }

    private Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        Map<Integer, Set<Integer>> nodeToParents = new HashMap<>();
        int u, v;
        for (int[] edge : edges) {
            u = edge[1]; // Child.
            v = edge[0]; // Parent.
            nodeToParents.putIfAbsent(u, new HashSet<>());
            nodeToParents.putIfAbsent(v, new HashSet<>());
            nodeToParents.get(u).add(v);
        }
        return nodeToParents;
    }

    public List<Integer> get01ParentNode(int[][] edges) {
        Map<Integer, Set<Integer>> nodeToParents = buildGraph(edges);
        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : nodeToParents.entrySet())
            if (entry.getValue().size() <= 1)
                resultList.add(entry.getKey());
        return resultList;
    }

    public boolean hasCommonAncestor(int[][] edges, int i, int j) {

        Map<Integer, Set<Integer>> nodeToParents = buildGraph(edges);
        Set<Integer> visitedOfI = new HashSet<>(); // Mark ancestors of i as visited.
        Set<Integer> visitedOfJ = new HashSet<>(); // Mark ancestors of j as visited.
        markAncestorsVisited(visitedOfJ, i, visitedOfI, nodeToParents);
        return markAncestorsVisited(visitedOfI, j, visitedOfJ, nodeToParents);
    }

    private boolean markAncestorsVisited(Set<Integer> visitedOfOther, int root, Set<Integer> visited, Map<Integer, Set<Integer>> nodeToParents) {
        for (int parent : nodeToParents.get(root)) {
            if (visitedOfOther.contains(parent))
                return true;
            if (visited.contains(parent))
                continue;
            visited.add(parent);
            if (markAncestorsVisited(visitedOfOther, root, visited, nodeToParents))
                return true;
        }
        return false;
    }

    public int getFarthestAncestor(int[][] edges, int node) {

        Map<Integer, Set<Integer>> nodeToParents = buildGraph(edges);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int result = -1;

        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result = curr;
            for (int adj : nodeToParents.get(curr)) {
                if (visited.contains(adj))
                    continue;
                queue.offer(adj);
                visited.add(adj);
            }
        }

        return result;
    }
}
