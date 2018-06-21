package Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * input: people[][] (height, k people in front who are not lower)
 * output: reconstructed queue
 * pick out tallest group and sort them, each guy's index will be just as same as his k value
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        int[][] result = new int[people.length][people[0].length];
        int ri = 0;
        for (int[] person : list) {
            result[ri++] = person;
        }
        return result;
    }
}
