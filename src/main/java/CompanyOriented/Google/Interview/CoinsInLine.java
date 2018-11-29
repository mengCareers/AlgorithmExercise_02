package CompanyOriented.Google.Interview;

/*
n coins in a line, two players take turns to take 1 or 2 coins from right side until no more coins left
the player who take the last coin wins
 */
/*

 */
public class CoinsInLine {
    public boolean canFirstWin(int n) {
        return memorySearch(n, new int[n]);
    }

    private boolean memorySearch(int n, int[] state) { // 0 empty, 1 f, 2 t

        if (state[n] != 0) {
            return (state[n] == 2);
        }

        if (n <= 0) {
            state[n] = 1;
        } else if (n == 1 || n == 2) {
            state[n] = 2;
        } else if (n == 3) {
            state[n] = 1;
        } else {
            if (memorySearch(n - 2, state) && memorySearch(n - 3, state) ||
                    (memorySearch(n - 3, state) && memorySearch(n - 4, state)))
                state[n] = 2;
            else
                state[n] = 1;
        }

        return (state[n] == 2);
    }
}
