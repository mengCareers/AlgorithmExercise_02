package CompanyOriented.Google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * input : equations[][], values[], queries[][]
 * use map,
 */
public class EvaluateDivision {
    private HashMap<String, String> father = new HashMap<>();
    private HashMap<String, Double> valMap = new HashMap<>();

    public double[] calcEquationUF(String[][] equations, double[] values, String[][] queries) {
        double[] result = new double[queries.length];
        if (values.length == 0 || result.length == 0) {
            return result;
        }
        for (int i = 0; i < values.length; i++) {
            String eq1 = equations[i][0];
            String eq2 = equations[i][1];
            if (!valMap.containsKey(eq1) && !valMap.containsKey(eq2)) {
                father.put(eq1, eq1);
                father.put(eq2, eq2);
                valMap.put(eq1, values[i]);
                valMap.put(eq2, 1.0);
            } else if (!valMap.containsKey(eq1)) {
                father.put(eq1, eq1);
                valMap.put(eq1, valMap.get(eq2) * values[i]);
            } else if (!valMap.containsKey(eq2)) {
                father.put(eq2, eq2);
                valMap.put(eq2, valMap.get(eq1) / values[i]);
            } else {
                String fa = find(eq1);
                for (String key : father.keySet()) {
                    if (find(key).equals(fa)) {
                        valMap.put(key, valMap.get(key) * values[i] * valMap.get(eq2));
                    }
                }
            }

            union(eq1, eq2);
        }
        for (int i = 0; i < queries.length; i++) {
            if (!valMap.containsKey(queries[i][0]) || !valMap.containsKey(queries[i][1]) || !find(queries[i][0]).equals(find(queries[i][1]))) {
                result[i] = -1.0;
            } else {
                result[i] = valMap.get(queries[i][0]) / valMap.get(queries[i][1]);
            }
        }
        return result;
    }

    private void union(String a, String b) {
        String fa = find(a);
        String fb = find(b);
        if (!fa.equals(fb)) {
            father.put(fb, fa);
        }
    }

    private String find(String a) {
        String parent = father.get(a);
        while (!parent.equals(father.get(parent))) {
            parent = father.get(parent);
        }

        String tmp = "";
        String fa = father.get(a);
        while (!fa.equals(father.get(fa))) {
            tmp = father.get(fa);
            father.put(fa, parent);
            fa = tmp;
        }

        return parent;
    }

    public static void main(String[] args) {
        EvaluateDivision inst = new EvaluateDivision();
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        inst.calcEquation(equations, values, queries);
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String u = equations[i][0], v = equations[i][1];
            if (!graph.containsKey(u)) {
                graph.put(u, new HashMap<>());
            }
            if (!graph.containsKey(v)) {
                graph.put(v, new HashMap<>());
            }
            graph.get(u).put(v, values[i]);
            graph.get(v).put(u, 1.0 / values[i]);
        }

        double[] result = new double[queries.length];
        int ri = 0;
        for (String[] query : queries) {
            String start = query[0], end = query[1];
            result[ri++] = calcEquationFrom(graph, start, end, new HashSet<>());
        }
        return result;

    }

    private double calcEquationFrom(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {

        if (!graph.containsKey(start) || visited.contains(start)) {
            return -1.0;
        }
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        visited.add(start);
        for (String neighbour : graph.get(start).keySet()) {
            double val = calcEquationFrom(graph, neighbour, end, visited);
            if (val == -1.0) {
                continue;
            }
            return val * graph.get(start).get(neighbour);
        }
        return -1.0;

    }
}
