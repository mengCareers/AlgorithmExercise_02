package Airbnb;

import java.util.*;

/*
day  3
earn 1 5 5
cost 2 1 4
start energy 4


if (cost[i] > earn[i]) we dont work
 */
public class CityAttractions {

    public static void main(String[] args) {
        List<Integer> beauty = new ArrayList<>();
        beauty.add(3);
        beauty.add(7);
        beauty.add(5);
        beauty.add(6);
        beauty.add(8);
        Integer[] uarr = {0, 2, 2, 0, 1};
        Integer[] varr = {1, 0, 3, 4, 3};
        Integer[] tarr = {10, 16, 13, 20, 15};
        int answer = findBestPath(5, 5, 60, beauty, Arrays.asList(uarr), Arrays.asList(varr), Arrays.asList(tarr));
        System.out.println(answer);
    }

    static int maxBeautyCollected = 0;
    static Map<Integer, Map<Integer, Integer>> adjacentList;

    static int findBestPath(int n, int m, int max_t, List<Integer> beauty, List<Integer> u, List<Integer> v, List<Integer> t) {

        adjacentList = new HashMap<>(); // <startNode, <endNode, weight>>
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int start = u.get(i);
            int end = v.get(i);
            adjacentList.putIfAbsent(start, new HashMap<>());
            adjacentList.putIfAbsent(end, new HashMap<>());
            adjacentList.get(start).put(end, t.get(i));
            adjacentList.get(end).put(start, t.get(i));
        }

        dfs(0, visited, max_t, beauty.get(0), beauty);

        return maxBeautyCollected;
    }

    static void dfs(int cur, Set<Integer> visited, int timeRemained, int beautyCollected, List<Integer> beauty) {
        if (timeRemained < 0) {
            return;
        }
        if (beautyCollected > maxBeautyCollected) {
            maxBeautyCollected = beautyCollected;
        }
        if (adjacentList.containsKey(cur)) {
            for (int neighbour : adjacentList.get(cur).keySet()) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
//                    if (neighbour == 0) {
//                        dfs(neighbour, visited, timeRemained - adjacentList.get(cur).get(neighbour), beautyCollected + beauty.get(neighbour), beauty);
//                    } else
                        dfs(neighbour, visited, timeRemained - adjacentList.get(cur).get(neighbour) * 2, beautyCollected + beauty.get(neighbour), beauty);
                    visited.remove(neighbour);
                }
            }
        }
    }
}
