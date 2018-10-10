package CompanyOriented.Karat;

import CompanyOriented.Google.TreeNode;

import java.util.*;

/**

 */
public class ForestLike {
    public static void main(String[] args) {
        ForestLike inst = new ForestLike();
        int[][] input = {{1, 4}, {1, 5}, {2, 5}, {3, 6}, {6, 7}};
        int[] result = inst.get01ParentTreeNodes(input);
        for (int node : result)
            System.out.print(node + ", ");
        System.out.println();
        System.out.println(inst.hasCommonAncestor(input, 4, 5));
        System.out.println(inst.hasCommonAncestor(input, 7, 5));
        System.out.println(inst.farthestAncestor(input, 1));

    }

    /**
     * use a map to store the relationship, key: node, value: number of parents
     *
     * @param input input[0] = input[1].parent
     * @return
     */
    Set<Integer> nodes;

    public int[] get01ParentTreeNodes(int[][] input) {

        Map<Integer, Integer> map = new HashMap<>(); // key: node's value, value: number of parents of node
        List<Integer> resultList = new ArrayList<>();
        nodes = new HashSet<>();
        for (int[] array : input) {
            nodes.add(array[0]);
            nodes.add(array[1]);

            map.put(array[1], map.getOrDefault(array[1], 0) + 1);
        }

        for (int node : nodes) {
            if (!map.containsKey(node) || map.get(node) == 1) {
                resultList.add(node);

            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    /**
     * we have learned how to get LCA in a tree
     * for ForestLike, we checked if i, j in the same treeLike
     * if it is, LCA for tree
     * parent to child map is nee
     *
     * @param i
     * @param j
     * @return
     */
    Map<Integer, List<Integer>> childToParents;
    Map<Integer, Set<Integer>> parentToChildren;

    public int hasCommonAncestor(int[][] input, int i, int j) {
        childToParents = new HashMap<>(); // key: node's value, value: parent nodes' values
        parentToChildren = new HashMap<>(); // key: parent node's value, value: children nodes' values
        for (int[] array : input) {
            childToParents.putIfAbsent(array[1], new ArrayList<>());
            childToParents.get(array[1]).add(array[0]); // maybe we can reuse the above
            parentToChildren.putIfAbsent(array[0], new HashSet<>());
            parentToChildren.get(array[0]).add(array[1]);
        }
        // for a node i , dfs on its parents, and see if parent contains j
        boolean[] visitedOfI = bfs(i);// assume they are from 1..n
        int commonAncestor = bfs(j, visitedOfI);
        return commonAncestor;
    }

    private boolean[] bfs(int start) {
        boolean[] visited = new boolean[nodes.size()];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (!childToParents.containsKey(node))
                continue;
            for (int parent : childToParents.get(node)) {
                if (!visited[parent]) {
                    visited[parent] = true;
                    queue.offer(parent);
                }
            }
        }
        return visited;
    }

    private int bfs(int start, boolean[] visitedByOther) {
        boolean[] visited = new boolean[nodes.size()];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (!childToParents.containsKey(node))
                continue;
            for (int parent : childToParents.get(node)) {
                if (visitedByOther[parent]) {
                    return parent;
                }
                if (!visited[parent]) {
                    visited[parent] = true;
                    queue.offer(parent);
                }
            }
        }
        return -1;
    }

    /**
     * farthest ancestor is ancestor, we get all anscestors and pick the farthest one
     * we can apply BFS
     *
     * @param input
     * @param i
     * @return
     */
    public int farthestAncestor(int[][] input, int i) {
        boolean[] visited = new boolean[nodes.size()];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(i);
        int node = i;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (!childToParents.containsKey(node))
                continue;
            for (int parent : childToParents.get(node)) {
                if (!visited[parent]) {
                    visited[parent] = true;
                    queue.offer(parent);
                }
            }
        }
        return node;
    }
}
