package CompanyOriented.Pinterest;

import java.util.*;

/*
 * Given a map which maps node to a list of nodes such that they are similar to each other, return similarity sets such that
 * nodes in a set are similar to each other.
 * Similarity is commutative.
 * e.g.
 * <a,[b,c,d]>
 * <x,[y,z]>
 * <o, z>
 * output:
 *  [a,b,c,d]
 *  [x,y,z,o]
 * */

public class SimilaritySet {
    public static void main(String[] args) {
        Map<String, List<String>> similarities = new HashMap<>();
        similarities.put("a", Arrays.asList("b", "c", "d"));
        similarities.put("x", Arrays.asList("y", "z"));
        similarities.put("o", Arrays.asList("z"));
        List<List<String>> result = getSimilaritySet(similarities);
        System.out.println(result);
    }

    public static List<List<String>> getSimilaritySet(Map<String, List<String>> similarities) {
        Map<String, List<String>> adjacentListMap = new HashMap<>(); // key: node, value: adjacent nodes.
        for (String keyNode : similarities.keySet()) {
            for (String neighbour : similarities.get(keyNode)) {
                adjacentListMap.putIfAbsent(keyNode, new ArrayList<>());
                adjacentListMap.get(keyNode).add(neighbour);
                adjacentListMap.putIfAbsent(neighbour, new ArrayList<>());
                adjacentListMap.get(neighbour).add(keyNode);
            }
        }
        Set<String> visitedNodes = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        Set<String> visited;
        for (String keyNode : similarities.keySet()) {
            if (visitedNodes.contains(keyNode))
                continue;
            visited = new HashSet<>();
            dfs(adjacentListMap, keyNode, visited);
            visitedNodes.addAll(visited);
            result.add(new ArrayList<>(visited));
        }
        return result;
    }

    private static void dfs(Map<String, List<String>> adjacentListMap, String node, Set<String> visited) {
        visited.add(node);
        for (String neighbour : adjacentListMap.get(node)) {
            if (!visited.contains(neighbour))
                dfs(adjacentListMap, neighbour, visited);
        }
    }
}
