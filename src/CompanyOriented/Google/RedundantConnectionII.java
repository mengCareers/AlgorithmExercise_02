package CompanyOriented.Google;

import java.util.Arrays;

/**
 * LOGICAL THINKING
 * • There are 3 cases for Redundant Connection:
 * case 1 : two-parent problem such that an error node is with two parents
 * case 2 : cyclic problem such that there is a cycle in the graph
 * case 3 : two-parent and cyclic problem
 * • If we mark
 * indexFirst as the index of the edge connecting the first parent we met of a node to the node itself,
 * indexSecond as the index of the edge connecting the other parent of the node if existing to the node itself,
 * indexMakeCycle as the index of the edge that forms a cycle, i.e., without it, the cycle cannot be formed,
 * the result will be
 * if (indexMakeCycle == -1) { // case 1
 * return edges[indexSecond];
 * }
 * if (indexSecond == -1) { // case 2
 * return edges[indexMakeCycle];
 * }
 * return edges[indexFirst]; // case 3
 * The explanation of the case 3 : We do not update incomingIndex[v] with indexSecond, i.e., we ignore edges[indexSecond]. Even if we ignore edges[indexSecond], the cycle still exists. So edges[indexFirst]is the one should be removed rather than edges[indexSecond].
 * • Arrays defined:
 * incomingIndex[]is used to detect the node with two parents if it exists, i.e., the value of indexFirst and indexSecond.
 * parent[] is used in Union Find to detect the cycle if it exists, i.e., the value of indexMakeCycle.
 */
public class RedundantConnectionII {
    /*
    there are 3 cases for redundant:
    case 1: 2-parent
    case 2: cyclic
    case 3: 2-parent and cyclic
    */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int indexFirst = -1, indexSecond = -1, indexLast = -1;
        int[] indexIncomingArr = new int[n + 1];
        Arrays.fill(indexIncomingArr, -1);
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int u = edges[i][0], v = edges[i][1];
            if (indexIncomingArr[v] != -1) { // ignore indexSecond
                indexFirst = indexIncomingArr[v];
                indexSecond = i;
            } else {
                indexIncomingArr[v] = i;
                if (find(parent, u) == v) { // cyclic
                    indexLast = i;
                } else {
                    parent[v] = find(parent, u);
                }
            }
        }

        if (indexLast == -1) { // case 1
            return edges[indexSecond];
        }
        if (indexSecond == -1) { // case 2
            return edges[indexLast];
        }
        return edges[indexFirst];
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
