package CompanyOriented.Amazon;

/**
 * n machines
 * choose any m machines and pass one dress of each to one of its adjacent
 * output: min # of moves to make all have the same # of dresses OR -1
 */
/*
负载均衡
先看能否，能的话 avg
 */
public class SuperWashingMachines {

    public int findMinMoves(int[] machines) {

        int[] cumulativeSum = getCumulativeSum(machines);

        if (cumulativeSum[machines.length - 1] % machines.length != 0) {
            return -1;
        }
        int avg = cumulativeSum[machines.length - 1] / machines.length;
        int leftSum = 0, rightSum = 0, leftDist = 0, rightDist = 0;
        int dist = 0, minDistToContainsAllDists = Integer.MIN_VALUE;

        for (int k = 0; k < machines.length; k++) {
            leftSum = (k == 0) ? 0 : cumulativeSum[k - 1];
            leftDist = leftSum - k * avg;
            rightSum = cumulativeSum[machines.length - 1] - cumulativeSum[k];
            rightDist = rightSum - (machines.length - 1 - k) * avg;
            if (leftDist > 0 && rightDist > 0) {
                dist = Math.max(leftDist, rightDist);
            } else if (leftDist < 0 && rightDist < 0) {
                dist = -leftDist - rightDist;
            } else {
                dist = Math.max(Math.abs(leftDist), Math.abs(rightDist));
            }
            minDistToContainsAllDists = Math.max(minDistToContainsAllDists, dist);
        }

        return minDistToContainsAllDists;
    }

    private int[] getCumulativeSum(int[] machines) {
        int[] cumulativeSum = new int[machines.length];
        for (int i = 0; i < machines.length; i++) {
            cumulativeSum[i] = (i == 0) ? machines[i] : cumulativeSum[i - 1] + machines[i];
        }
        return cumulativeSum;
    }

}
