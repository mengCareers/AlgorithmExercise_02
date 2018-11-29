package CompanyOriented.Salesforce;

import java.util.Arrays;

/**
 * input: scores[], lowerLimits[], upperLimits
 * output:offers[]
 */
public class PsychometricTesting {
    public int[] countOffers(int[] scores, int[] lower, int[] higher) {

        /* Corner cases to add. */

        int q = lower.length, n = scores.length;
        int[] offers = new int[q];

        Arrays.sort(scores);
        for (int i = 0; i < q; i++) {
            int lo = Arrays.binarySearch(scores, lower[i]);
            int hi = Arrays.binarySearch(scores, higher[i]);
            offers[i] = hi - lo;
        }

        return offers;
    }
}
/*
 * scores: 1 2 3 6 7
 * lower: 3 -- 2
 * higher: 5 -- 3
 * 3 - 2
 * */
