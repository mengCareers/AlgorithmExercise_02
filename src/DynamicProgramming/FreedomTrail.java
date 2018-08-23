package DynamicProgramming;

public class FreedomTrail {
    public int findRotateSteps(String ring, String key) {

        // corner case to add

        int keyLength = key.length(), ringLength = ring.length();
        int[][] state = new int[keyLength + 1][ringLength];
        for (int i = keyLength - 1; i >= 0; i--) {
            for (int j = 0; j < ringLength; j++) {
                state[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < ringLength; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(k - j);
                        int steps = Math.min(diff, ringLength - diff);
                        state[i][j] = Math.min(state[i][j], state[i + 1][k] + steps);
                    }
                }
            }
        }

        return state[0][0] + keyLength;
    }
}
