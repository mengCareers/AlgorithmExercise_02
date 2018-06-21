package Google;

import java.util.*;

/**
 * input: routes[][], S, T
 * output: least number of buses we must take from S to T OR -1
 * State : dp[i] as least # of buses we must take from S to i
 * End State: dp[T]
 * State Transformation : all steps next to i
 * Standing at i,
 * we can go forward to any step that i can reach,
 * and keep track of the # of bus information, whenever we meet T, return the #
 * <p>
 * Our problem is that, how do we detect the next steps of i?
 * e.g. we are standing 1,
 * we next steps for 1 are buses pass 1
 * bus b1 1, 5, 7
 * bus b2 1, 3, 4
 * if we see each bus as a node, and build a graph, edge is connection
 * use map to represent the graph, i.e., <bus, list of connected buses>
 * if we take bus (containing S), and check all connected buses to it, check if have T
 * <p>
 * T is targets and so do S
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < routes.length; i++) {
            Arrays.sort(routes[i]);
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                queue.add(i);
                visited.add(i);
            }
            if (Arrays.binarySearch(routes[i], T) >= 0) {
                targets.add(i);
            }
        }

        buildGraph(graph, routes);

        int level = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int curBus = queue.poll();
                if (targets.contains(curBus)) {
                    return level;
                }
                if (graph.containsKey(curBus)) {
                    for (int nextBus : graph.get(curBus)) {
                        if (!visited.contains(nextBus)) {
                            queue.add(nextBus);
                            visited.add(nextBus);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }

    /**
     * @param routeA
     * @param routeB
     * @return true if two routes have at least one same stop
     */
    private boolean isConnected(int[] routeA, int[] routeB) {
        int ai = 0, bi = 0;
        while (ai < routeA.length && bi < routeB.length) {
            if (routeA[ai] == routeB[bi]) {
                return true;
            } else if (routeA[ai] < routeB[bi]) {
                ai++;
            } else {
                bi++;
            }
        }
        return false;
    }

    private void buildGraph(Map<Integer, List<Integer>> graph, int[][] routes) {
        for (int i = 0; i < routes.length - 1; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if (isConnected(routes[i], routes[j])) {
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<>());
                    }
                    graph.get(i).add(j);
                    if (!graph.containsKey(j)) {
                        graph.put(j, new ArrayList<>());
                    }
                    graph.get(j).add(i);
                }
            }
        }
    }
}
