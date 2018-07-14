package Google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * State : dp[x] as the shortest instruction sequence to x
 * Aim state : dp[target] as the shortest instruction sequence to target
 * State Transfer :
 * dp[x] = dp[perX] + 1;
 * preX are list of positions that can reach x in the next step
 * <p>
 * To get the values of states :
 * <p>
 * start point : 0
 * for each step i,
 * {
 * we get positions (either A or R) it can reach in the next step,
 * if A,
 * position += speed,
 * speed *= 2
 * if R,
 * position remains
 * speed = -1 if speed > 0
 * speed = 1 if speed <= 0
 * }
 * end point: target
 * This is a shortest path problem, which can be solved by BFS with the record of level information.
 * We encapsulate StateNode class with position and speed attritutes.Then the start node is (0, 1).
 * Please note that StateNode with the same position but different speeds are considered as different StateNodes.
 * Math.abs(newPosition - target) < target is a implicit restriction of the valid answer. Or else, we will get TLE.
 */
public class RaceCar {
    public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        Queue<StateNode> queue = new LinkedList<>();
        queue.add(new StateNode(1, 0));
        int distance = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                StateNode cur = queue.poll();
                if (cur.position == target) {
                    return distance;
                }
                // if A
                int nextPosition = cur.position + cur.speed;
                int nextSpeed = cur.speed * 2;
                if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                    visited.add(nextSpeed + "," + nextPosition);
                    queue.offer(new StateNode(nextSpeed, nextPosition));
                }
                // if R
                nextPosition = cur.position;
                nextSpeed = cur.speed > 0 ? -1 : 1;
                if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                    visited.add(nextSpeed + "," + nextPosition);
                    queue.offer(new StateNode(nextSpeed, nextPosition));
                }
            }
            distance++;
        }
        return -1;
    }

    class StateNode {
        int speed;
        int position;

        public StateNode(int speed, int position) {
            this.speed = speed;
            this.position = position;
        }
    }

    public static void main(String[] args) {
        RaceCar inst = new RaceCar();
        System.out.println(inst.racecar(3));
    }

}
