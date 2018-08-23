package Amazon;

import java.util.*;

/*
compute avg of five highest score of each student
 */
public class HighFive {
    public Map<Integer, Double> highFive(Record[] results) {

        Map<Integer, PriorityQueue<Integer>> sidToScores = new HashMap<>();
        Set<Integer> sidSet = new HashSet<>();
        int sid, score;
        for (Record result : results) {
            sid = result.sid;
            score = result.score;
            sidSet.add(sid);
            if (!sidToScores.containsKey(sid)) {
                sidToScores.put(sid, new PriorityQueue<>());
            }
            sidToScores.get(sid).offer(score);
            if (sidToScores.get(sid).size() > 5) {
                sidToScores.get(sid).poll();
            }
        }

        Map<Integer, Double> sidToAvg = new HashMap<>();
        PriorityQueue<Integer> pq;
        double sumScore;
        for (int id : sidToScores.keySet()) {
            sumScore = 0;
            pq = sidToScores.get(id);
            while (!pq.isEmpty()) {
                sumScore += pq.poll();
            }
            sidToAvg.put(id, sumScore / 5);
        }

        return sidToAvg;
    }

}

class Record {
    int sid;
    int score;

    public Record(int sid, int score) {
        this.sid = sid;
        this.score = score;
    }
}