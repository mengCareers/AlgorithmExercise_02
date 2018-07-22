package Contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WalkingRobotSImulation {

    public static void main(String[] args) {
        WalkingRobotSImulation inst = new WalkingRobotSImulation();
        int[] commands = {-2, -1, -2, 3, 7};
        int[][] obstacles = {{1, -3}, {2, -3}, {4, 0}, {-2, 5}, {-5, 2}, {0, 0}, {4, -4}, {-2, -5}, {-1, -2}, {0, 2}};
        System.out.println(inst.robotSim(commands, obstacles));
    }

    private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int directPtr, x, y, maxDist;

    public int robotSim(int[] commands, int[][] obstacles) {

        directPtr = 0;
        x = 0;
        y = 0;
        maxDist = 0;
        Map<Integer, Set<Integer>> mapObstacles = new HashMap<>();
        for (int[] obstacle : obstacles) {
            if (obstacle[0] == 0 && obstacle[1] == 0) {
                continue;
            }
            if (!mapObstacles.containsKey(obstacle[0])) {
                mapObstacles.put(obstacle[0], new HashSet<>());
            }
            mapObstacles.get(obstacle[0]).add(obstacle[1]);
        }

        followCommand(commands, 0, mapObstacles);

        return maxDist;
    }

    private void followCommand(int[] commands, int ci, Map<Integer, Set<Integer>> mapObstacles) {

        if (ci == commands.length) {
            return;
        }

        if (commands[ci] == -1) {
            directPtr++;
            if (directPtr > 3) {
                directPtr -= 4;
            }
        } else if (commands[ci] == -2) {
            directPtr--;
            if (directPtr < 0) {
                directPtr += 4;
            }
        } else if (commands[ci] >= 1 && commands[ci] <= 9) {
            int i = 0;
            while (i < commands[ci] && (!mapObstacles.containsKey(x) || !mapObstacles.get(x).contains(y))) {
                x += directions[directPtr][0];
                y += directions[directPtr][1];
                i++;
            }
            if (mapObstacles.containsKey(x) && mapObstacles.get(x).contains(y)) {
                x -= directions[directPtr][0];
                y -= directions[directPtr][1];
            }
            maxDist = Math.max(maxDist, x * x + y * y);
        }
        followCommand(commands, ci + 1, mapObstacles);
    }
}
