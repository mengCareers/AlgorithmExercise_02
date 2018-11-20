package DFS;

public class RobotRoomCleaner {
//    private static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//
//    public void cleanRoom(Robot robot) {
//        cleanRoomFrom(0, 0, 0, robot, new HashSet<>());
//    }
//
//    private void cleanRoomFrom(int x, int y, int direction, Robot robot, Set<String> visited) {
//
//        visited.add(x + "," + y);
//        robot.clean();
//        int nx, ny;
//
//        for (int times = 0; times < 4; times++) {
//            nx = x + directions[direction][0];
//            ny = y + directions[direction][1];
//            if (visited.contains(nx + "," + ny)) {
//                direction++;
//                direction %= 4;
//                robot.turnRight();
//                continue;
//            }
//            if (robot.move()) {
//                cleanRoomFrom(nx, ny, direction, robot, visited);
//                robot.turnRight();
//                robot.turnRight();
//                robot.move();
//                robot.turnLeft();
//                robot.turnLeft();
//            }
//            direction++;
//            direction %= 4;
//            robot.turnRight();
//        }
//
//    }
}
