package CompanyOriented.Facebook;

import java.util.*;

/**
 * input: tasks, n
 * between two same tasks, there must be >= n intervals
 * i, i + n + 1
 * output: min # of intervals needed
 */
/*
e.g. A A A B B B B
B A * B A * B A * B
frequencies
B 4 A 3
settle B down
B _ _ B _ _ B _ _ B
settle A down
B A _ B A _ B A _ B
add edle *
In my previous way, it is hard to do with intervals
So how about we usually do with the element with highest freq in each SLOT
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<Integer> freqPQ = new PriorityQueue<>(26, Collections.reverseOrder());
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                freqPQ.offer(map[i]);
            }
        }
        int result = 0;
        while (!freqPQ.isEmpty()) {
            int i = 0;
            List<Integer> toPutBack = new ArrayList<>();
            while (i <= n) {
                int curFreq = freqPQ.poll();
                curFreq--;
                if (curFreq >= 1) {
                    toPutBack.add(curFreq);
                }
                result++;
                if (freqPQ.isEmpty()) {
                    break;
                }
                i++;
            }
            for (int freq : toPutBack) {
                freqPQ.offer(freq);
            }
            while (i < n && !freqPQ.isEmpty()) {
                i++;
                result++;
            }
        }
        return result;
    }

}
