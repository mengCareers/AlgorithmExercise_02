package CompanyOriented.Google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
q[i]: quality of worker i
w[i]: wage of worker i
K: K workers to pick such that min cost to maintain 2 rules
            rule1 w[i] : w[j] = q[i] : q[j]
            rule2 get[i] >= w[i]
 */
/*
Since rule1, w[i] : q[i] = w[j] : q[j]
      each worker has his own ratio
      we try pick these with minimum ratio,
      however, minimum ratio cannot promise the minimum cost for it is also relate to quality,
        so we try all possible result with each ratio

 */
public class MinimumCostToHireKWorkers {
    public double mincostToHireWorkers(int[] q, int[] w, int K) {

        int n = q.length;
        double[][] ratioQuality = new double[n][2];
        for (int i = 0; i < n; i++) {
            ratioQuality[i] = new double[2];
            ratioQuality[i][0] = (double) (w[i]) / q[i];
            ratioQuality[i][1] = q[i];
        }
        Arrays.sort(ratioQuality, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[0], o2[0]);
            }
        });
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });

        double minCost = Double.MAX_VALUE, qSum = 0;
        for (double[] curMan : ratioQuality) {
            qSum += curMan[1];
            priorityQueue.add(curMan[1]);
            if (priorityQueue.size() > K) {
                qSum -= priorityQueue.poll();
            }
            if (priorityQueue.size() == K) {
                minCost = Math.min(minCost, qSum * curMan[0]);
            }
        }

        return minCost;
    }
}
