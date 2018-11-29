package CompanyOriented.Google.Interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        double[][] ratioToQuality = new double[n][2];
        for (int i = 0; i < n; i++) {
            ratioToQuality[i][0] = (double) wage[i] / quality[i];
            ratioToQuality[i][1] = quality[i];
        }
        PriorityQueue<Double> pqGroup = new PriorityQueue<Double>(Collections.reverseOrder());
        // why sort by ratio
        double sumQuality = 0, minCost = Double.MAX_VALUE;
        Arrays.sort(ratioToQuality, (a, b) -> Double.compare(a[0], b[0]));
        for (double[] curRatioQuality : ratioToQuality) {
            sumQuality += curRatioQuality[1];
            pqGroup.add(curRatioQuality[1]);
            if (pqGroup.size() > K) {
                sumQuality -= pqGroup.poll();
            }
            if (pqGroup.size() == K) {
                minCost = Math.min(minCost, sumQuality * curRatioQuality[0]);
            }
        }
        return minCost;
    }
}
