package CompanyOriented.Facebook;

/**
 * people labeled 0 to n - 1, one of them is celebrity
 * celebrity: others know him but he dk others
 * helper function boolean knows(int a, int b) true if a knows b
 * output: celebrity's label or -1
 */
/*
find the most possible one, check if it is valid
if a knows b,
a must not be celebrity, b may be celebrity
 */
public class FindTheCelebrity {

    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == candidate) {
                continue;
            }
            if (!knows(i, candidate) || knows(candidate, i)) {
                return -1;
            }
        }
        return candidate;
    }

    boolean knows(int a, int b) {
        return true;
    }
}
