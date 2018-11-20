package DFS;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        int n = 1, k = 1;
        Test inst = new Test();
        inst.crackSafe(n, k);
    }

    public String crackSafe(int n, int k) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < k; i++) {
            result.append("0");
        }
        Set<String> visited = new HashSet<>();
        visited.add(result.toString());
        crackSafeFrom(0, result, n, k, (int) Math.pow(k, n), visited);

        return result.toString();
    }

    private boolean crackSafeFrom(int i, StringBuilder result, int n, int k, int total, Set<String> visited) {

        if (i == total) {
            return true;
        }

        String prevNode = result.substring(result.length() - n + 1);

        for (char c = '0'; c < '0' + k; c++) {
            if (!visited.contains(prevNode + c)) {
                result.append(c);
                visited.add(prevNode + c);
                if (crackSafeFrom(i + 1, result, n, k, total, visited)) {
                    return true;
                }
                result.deleteCharAt(result.length() - 1);
                visited.remove(prevNode + c);
            }
        }
        return false;
    }
}
