package Google;

/**
 * e.g.
 If we start from startStation, and we run out of oil at startStation + stepsForward - 1, i.e., we cannot reach at startStation + stepsForward from startStation, we can jump to startStation + stepsForward directly as the new start rather than startStation + 1.
 The reason is that if the car from startStation can't reach at the station target, cars from station [startStation + 1, target - 1] cannot reach at station target, either.
 */
public class GasStation {
    public static void main(String[] args) {
        GasStation inst = new GasStation();
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        inst.canCompleteCircuit(gas, cost);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, startPosition = 0, stepsForward = 0, curOil = 0, curPos = 0;
        for (; startPosition < n; startPosition = startPosition + stepsForward) {
            curOil = 0;
            for (stepsForward = 1; stepsForward <= n; stepsForward++) {
                curPos = (startPosition + stepsForward - 1) % n;
                curOil += gas[curPos] - cost[curPos];
                if (curOil < 0) {
                    break;
                }
            }
            if (stepsForward > n) {
                return startPosition;
            }
        }
        return -1;
    }
}
