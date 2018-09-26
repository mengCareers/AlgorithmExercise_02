package CompanyOriented.Facebook;

/**
 * input: nums, k (size of each subarray)
 *        maximize the sum of all 3 * k entries
 * output: starting indices of all intervals
 */
/*
撞 1 2 1 2 6 7 5 1, k = 2
1,2 = 3
1,2 = 3                     || 2,6 = 8 || 6,7 = 13 (stop for there should be 3 * k)
6,7 = 13, 7,5 = 12, 5,1 = 6

 */

/**
 * MENTOR: natural to consider an array W of each k-size interval's sum
 * THEN
 Given some array W and an integer K, what is the lexicographically
 smallest tuple of indices
 (i, j, k) with i + K <= j and j + K <= k
 that maximizes W[i] + W[j] + W[k]
  * IF WE FIX j,
 */
public class MaximumSumof3NonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
return null;
    }
}
