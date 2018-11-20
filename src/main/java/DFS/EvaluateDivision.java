package DFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        int queriesLength = queries.length;
        double[] result = new double[queriesLength];
        String u, v;
        // build graph
        Map<String, Map<String, Double>> adjacentMap = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            u = equations[i][0];
            v = equations[i][1];
            adjacentMap.putIfAbsent(u, new HashMap<>());
            adjacentMap.get(u).put(v, values[i]);
            adjacentMap.putIfAbsent(v, new HashMap<>());
            adjacentMap.get(v).put(u, 1.0 / values[i]);
        }

        for (int i = 0; i < queriesLength; i++) {
            u = queries[i][0];
            v = queries[i][1];
            result[i] = calcEquationFrom(u, v, adjacentMap, new HashSet<>());
        }

        return result;
    }

    private double calcEquationFrom(String u, String v, Map<String, Map<String, Double>> adjacentMap, Set<String> visited) {
        if (!adjacentMap.containsKey(u) || visited.contains(u)) {
            return -1.0;
        }
        if (adjacentMap.get(u).containsKey(v)) {
            return adjacentMap.get(u).get(v);
        }
        visited.add(u);
        for (String neighbour : adjacentMap.get(u).keySet()) {
            double value = calcEquationFrom(neighbour, v, adjacentMap, visited);
            if (value == -1.0)
                continue;
            return value * adjacentMap.get(u).get(neighbour);
        }
        return -1.0;
    }
}
