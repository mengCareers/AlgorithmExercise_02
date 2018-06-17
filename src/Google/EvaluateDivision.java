package Google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * input : equations[][], values[], queries[][]
 * use map,
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        int number = 3;
        for (char i = '0'; i <= '8'; i++) {
            System.out.println(i - '0');
        }

//        EvaluateDivision inst = new EvaluateDivision();
//        String[][] equations = {{"a", "b"}, {"b", "c"}};
//        double[] values = {2.0, 3.0};
//        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
//        inst.calcEquation(equations, values, queries);
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            if (!map.containsKey(equations[i][0])) {
                map.put(equations[i][0], new HashMap<>());
            }
            if (!map.containsKey(equations[i][1])) {
                map.put(equations[i][1], new HashMap<>());
            }
            map.get(equations[i][0]).put(equations[i][1], values[i]);
            map.get(equations[i][1]).put(equations[i][0], 1.0 / values[i]);
        }
        double[] result = new double[queries.length];
        int ri = 0;
        for (int i = 0; i < queries.length; i++) {
            result[i] = calcEquationFrom(map, queries[i][0], queries[i][1], new HashSet<>());
        }
        return result;
    }

    private double calcEquationFrom(Map<String, Map<String, Double>> map, String x, String y, Set<String> visited) {
        if (!map.containsKey(x) || !map.containsKey(y) || visited.contains(x)) {
            return -1.0;
        }
        visited.add(x);
        if (map.get(x).containsKey(y)) {
            return map.get(x).get(y);
        }
        for (String k : map.get(x).keySet()) {
            double ans = calcEquationFrom(map, k, y, visited);
            if (ans != -1.0) {
                return map.get(x).get(k) * ans;
            }
        }
        return -1.0;
    }
}
