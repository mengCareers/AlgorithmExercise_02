package Contest;

/**
 * destination target miles east of the starting position
 * gas stations station[i], station[i][0] from start, stations[i][1] liters of gas
 */
/* MENTOR:
state: state[i] means the farthest distance we can reach with # of i stops
start state: state[0]
end state: state[stations.length]
goal : min(state[i] that >= target
state transition:
    at station i, state[i] is bigger one of the below:
            if we refuel, the problem becomes state[i - 1] + station[i][1]
            if we donot refuel, the problem remains state[i - 1] which is smaller than state[i - 1] + station[i][1]

 */
/* v2
fuel, position, # of stations used,

state[curPos][curFuel]: distanceCanReach
we want state[curPos][curFuel] >
for each fuel station station[i], we can add fuel or we don't
if we add, state[station[i][0]][f - (stations[i][0] - stations[i - 1][0]) + station[i][1]]
if we not, state[station[i][0]][f - (stations[i][0] - stations[i - 1][0])]

 */
/* v1
state: state[t][f] as # of stations needed with fuel f standing at s to reach target
start state: state[0][startFuel]
aim state: state[target][..]
end state: t == target || f == 0
state transition: standing at curPos
    if (fuel - (stations[i][0] - stations[i - 1][0]) >= 0) { // can reach

        state[i - 1][fuel] = Math.min(
            state[i][fuel - (stations[i][0] - stations[i - 1][0]) + stations[i][1])],
            state[i][
        )

    }

 */
public class MinimumNumberOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        /**
         * stopTimes t is changing, and it decides maxDistance
         * state: state[t] as maxDistance we can reach with stopTimes t
         * goal state: min t make state[t] >= target
         * state transition:
         *      for station[i],
         *          if we stop, the problem becomes to calculate
         *              state[t] + station[i][1];
         *          if we don't stop, the problem becomes to calculate
         *              state[t]; // smaller surely
         *      thus,
         *          state[t + 1] = max(state[t] + station[i][1])
         *
         */
        long[] state = new long[stations.length + 1];
        state[0] = startFuel;
        for (int i = 0; i < stations.length; i++) {
            for (int t = i; t >= 0 && state[t] >= stations[i][0]; t--) {
                state[t + 1] = Math.max(state[t + 1], state[t] + stations[i][1]);
            }
        }
        for (int t = 0; t <= stations.length; t++) {
            if (state[t] >= target) {
                return t;
            }
        }
        return -1;
    }
}
