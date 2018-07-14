package Contest;

import java.util.*;

/*
start point @
we can walk 4 directions by one step,
aim end: list[a b] meet[A B]

 */
public class ShortestPathToGetAllKeys {
    private final static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Map<Character, int[]> keyToPosition;
    Set<Character> keysGot;
    char[][] mat;
    int[] startPosition;

    public int shortestPathAllKeys(String[] grid) {

        keyToPosition = new HashMap<>();
        keysGot = new HashSet<>();
        int rows = grid.length, cols = grid[0].length();
        mat = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            mat[i] = grid[i].toCharArray();
        }
        startPosition = new int[2];

        String allKeys = getAllKeys();

        List<String> keysPermutations = permuteAllKeys(allKeys);

        int shortestPath = Integer.MAX_VALUE, curShortestPath = 0;
        for (String curKeys : keysPermutations) {
            keysGot.clear();
            curShortestPath = shortestPathForCurrentKeysPermutation(curKeys);
            if (curShortestPath < 0) {
                continue;
            }
            shortestPath = Math.min(shortestPath, curShortestPath);
        }

        return shortestPath == Integer.MAX_VALUE ? -1 : shortestPath;

    }

    public int shortestPathForCurrentKeysPermutation(String curKeys) {

        char[] keys = curKeys.toCharArray();
        int curX = startPosition[0], curY = startPosition[1];
        int aimX = 0, aimY = 0, minSteps = 0, curMinSteps = 0;

        for (char key : keys) {
            int[] position = keyToPosition.get(key);
            aimX = position[0];
            aimY = position[1];
            curMinSteps = shortestPathFromKeyToKey(curX, curY, aimX, aimY);
            if (curMinSteps < 0) {
                return -1;
            }
            minSteps += curMinSteps;
            curX = aimX;
            curY = aimY;
        }

        return minSteps;
    }

    private int shortestPathFromKeyToKey(int curX, int curY, int aimX, int aimY) {

        int minSteps = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        queue.offer(new int[]{curX, curY});
        visited[curX][curY] = true;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] curPos = queue.poll();
                for (int[] direction : directions) {
                    int nx = curPos[0] + direction[0];
                    int ny = curPos[1] + direction[1];
                    if (nx >= 0 && nx < mat.length && ny >= 0 && ny < mat[0].length && !visited[nx][ny] && mat[nx][ny] != '#') {
                        if (mat[nx][ny] >= 'A' && mat[nx][ny] <= 'Z') { // if lock
                            if (!isValid(mat[nx][ny], keysGot)) {
                                continue;
                            }
                        }
                        if (mat[nx][ny] >= 'a' && mat[nx][ny] <= 'z') { // if key
                            keysGot.add(mat[nx][ny]);
                            if (nx == aimX && ny == aimY) {
                                return minSteps + 1;
                            }
                        }
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }

    private boolean isValid(char curLock, Set<Character> keysGot) {
        if (keysGot.contains(Character.toLowerCase(curLock))) {
            return true;
        }
        return false;
    }

    private String getAllKeys() {
        StringBuilder keys = new StringBuilder();
        char tmpVal = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                tmpVal = mat[i][j];
                if (tmpVal >= 'a' && tmpVal <= 'z') {
                    keyToPosition.put(tmpVal, new int[]{i, j});
                    keys.append(tmpVal);
                } else if (tmpVal == '@') {
                    startPosition[0] = i;
                    startPosition[1] = j;
                }
            }
        }
        return keys.toString();
    }

    private List<String> permuteAllKeys(String allKeys) {
        List<String> permutations = new ArrayList<>();
        permuteFrom(allKeys, 0, new StringBuilder(), permutations);
        return permutations;
    }

    private void permuteFrom(String str, int curPos, StringBuilder curRes, List<String> allRes) {
        if (curPos == str.length()) {
            allRes.add(curRes.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (curRes.indexOf("" + str.charAt(i)) == -1) {
                curRes.append(str.charAt(i));
                permuteFrom(str, curPos + 1, curRes, allRes);
                curRes.deleteCharAt(curRes.length() - 1);
            }
        }
    }
}