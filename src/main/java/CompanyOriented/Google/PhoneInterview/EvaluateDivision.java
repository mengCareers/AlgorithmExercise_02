package CompanyOriented.Google.PhoneInterview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int numQueries = queries.length;
        double[] result = new double[numQueries];

        /* Build graph. */
        Map<String, Map<String, Double>> graph = buildGraph(equations, values); // Key: node, value: <node, weight>.

        /* Depth-first Search. */
        for (int i = 0; i < numQueries; i++) {
            Set<String> visited = new HashSet<>();
            visited.add(queries[i][0]);
            result[i] = findPathFrom(queries[i][0], queries[i][1], graph, visited);
        }

        return result;
    }

    private Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.length; i++) {
            String u = equations[i][0];
            String v = equations[i][1];
            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / values[i]);
        }

        return graph;
    }

    private double findPathFrom(String u, String v, Map<String, Map<String, Double>> graph, Set<String> visited) {

        /* Rejection case. */
        if (!graph.containsKey(u))
            return -1.0;

        /* Accepting case. */
        if (graph.get(u).containsKey(v))
            return graph.get(u).get(v);

        for (String adjNode : graph.get(u).keySet()) {
            if (!visited.contains(adjNode)) {
                visited.add(adjNode);
                double pathWeight = findPathFrom(adjNode, v, graph, visited);
                if (pathWeight != -1)
                    return graph.get(u).get(adjNode) * pathWeight;
            }
        }

        return -1.0;
    }

}
