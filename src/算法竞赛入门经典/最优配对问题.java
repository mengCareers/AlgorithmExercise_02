package 算法竞赛入门经典;

/*
N个点，配成N/2对，使得每个点恰好在一个点对中，所有点对亮点的距离之和应该尽量小

state[S] as answers for set S
 */
public class 最优配对问题 {

    class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private double getDistance(Node a, Node b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z));
    }

    public double shortestDistanceSumPair(Node[] dots) {

        int N = dots.length;
        double[] state = new double[N];

        for (int s = 0; s < (1 << N); s++) {
            int i;
            for (i = 0; i < N; i++) {
                if ((s & (1 << i)) != 0) {
                    break;
                }
            }
            for (int j = i + 1; j < N; j++) {
                if ((s & (1 << j)) != 0) {
                    state[s] = Math.min(state[s], getDistance(dots[i], dots[j]) + state[s ^ (1 << i) ^ (1 << j)]);
                }
            }
        }

        return state[N - 1];
    }
}
