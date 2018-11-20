package CompanyOriented.Facebook;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PerfectSquares {

    public static void main(String[] args) {
        PerfectSquares inst = new PerfectSquares();
        int n = 12;
        System.out.println(inst.numSquares01(n));
    }

    /*
state definition: transitive value to n
state function: state[i], min number of perfect square numbers sum to i
goal state: state[n]
base case: state[0] = 0
state transition: min(state[i - j * j] + 1) = state[i]
filling direction: i, j increasing
 */
    public int numSquares01(int n) {

        int[] state = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            state[i] = i;
            for (int j = 1; j <= i; j++) {
                if (j * j > i) {
                    break;
                }
                state[i] = Math.min(state[i - j * j] + 1, state[i]);
            }
        }

        return state[n];
    }

    /*
    state definition: transitive value to n
    state function: state[i], min number of perfect square numbers sum to i
    goal state: state[n]
    base case: state[0] = 0
    state transition: min(state[j] + 1 if (i - j) is square) = state[i]
    filling direction: i, j increasing
     */
    public int numSquares00(int n) {

        int[] state = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            state[i] = i;
            for (int j = 0; j < i; j++) {
                double extracted = Math.sqrt(i - j);
                if (extracted - Math.floor(extracted) == 0) {
                    state[i] = Math.min(state[j] + 1, state[i]);
                }
            }
        }

        return state[n];
    }

    public int numSquaresBFS(int n) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int sz = 0; sz < size; sz++) {
                int cur = queue.poll();
                if (cur > n) {
                    continue;
                }
                if (cur == n) {
                    return count;
                }
                for (int i = 1; cur + i * i <= n; i++) {
                    if (visited.contains(cur + i * i)) {
                        continue;
                    }
                    queue.add(cur + i * i);
                    visited.add(cur + i * i);
                }
            }
            count++;
        }

        return -1;
    }

}
