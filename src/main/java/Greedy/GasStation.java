package Greedy;

/**
 * input : gas, cost
 * output: the starting station index if we can travel around the circuit once in the clockwise direction
 * -1
 * constriction: gas & cost
 * we choose gas prior, i.e., the one with maximum gas
 * or cost prior, i.e., the one with smallest cost
 * <p>
 * If we are starting at gas[i], what is our best choice for now? we go as further as we could
 * If we run out of gas before we back to gas[i], we try gas[j] as start
 * such that j = i - 1, for A X B, if A cannot reach B, X cannot either
 */
public class GasStation {
    public static void main(String[] args) {
        GasStation inst = new GasStation();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        inst.canCompleteCircuit(gas, cost);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, stepsForward = 0;
        for (int startStation = 0; startStation < n; startStation += stepsForward) {
            int gasLeft = 0;
            for (stepsForward = 1; stepsForward <= n; stepsForward++) {
                int curStation = (startStation + stepsForward - 1) % n;
                gasLeft += gas[curStation] - cost[curStation];
                if (gasLeft < 0) {
                    break;
                }
            }
            if (stepsForward > n) {
                return startStation;
            }
        }
        return -1;
    }


    public int canCompleteCircuitES(int[] gas, int[] cost) {
        for (int startStation = gas.length - 1; startStation >= 0; startStation--) {
            int curOil = 0;
            int curStation = startStation;
            while (curOil + gas[curStation] - cost[curStation] >= 0) {
                curOil = curOil + gas[curStation] - cost[curStation];
                if (curStation == gas.length - 1) {
                    curStation = 0;
                } else {
                    curStation++;
                }
                if (curStation == startStation) {
                    return startStation;
                }
            }
        }
        return -1;
    }
}
