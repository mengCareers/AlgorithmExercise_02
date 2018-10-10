package CompanyOriented.Google.Extension.ShortestPath;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        int[][] flights = {{1, 2, 5}, {2, 3, 2}, {2, 4, 1}, {3, 1, 1}};
        int val = findCheapestPrice(3, flights, 1, 4, 5);
        System.out.println(val);
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // build graph
        Map<Integer, Map<Integer, Integer>> adjacentList = new HashMap<>(); // <city, <city reachable, flight cost>>
        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            int weight = flight[2];
            adjacentList.putIfAbsent(u, new HashMap<>());
            adjacentList.get(u).put(v, weight);
        }

        // bfs + heap implement Dijkstra
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>((a, b) -> (Integer.compare(a[0], b[0]))); // [distToSrc, u, stops remained]
        priorityQueue.add(new int[]{0, src, K});
        while (!priorityQueue.isEmpty()) {
            int[] tmp = priorityQueue.poll();
            int distToSrc = tmp[0];
            int u = tmp[1];
            int stopsRemained = tmp[2];
            if (u == dst)
                return distToSrc;
            if (stopsRemained >= 0) {
                Map<Integer, Integer> neighbours = adjacentList.getOrDefault(u, new HashMap<>());
                for (int v : neighbours.keySet()) {
                    int edgeWeight = neighbours.get(v);
                    priorityQueue.add(new int[]{distToSrc + edgeWeight, v, stopsRemained - 1});
                }
            }
        }

        return -1;
    }
}
