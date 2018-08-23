package GoldmanSachs;

import java.util.*;

/*
school allocate students according to scores highest to lowest,
 score_students map,
 score sort decreasingly,
 then
    for (score : scores) {
        for (sid : map.get(score)) {
            for (school : prefer.get(sid)) {
                allocate;
                if successfull:  seatsRemained --; studentsRemained --; break;
            }
        }
    }

 */
public class SchoolAllotment {
    static List<Integer> allocateSchools(List<Integer> schoolSeatsArray, List<Integer> studentScoreArray, List<List<Integer>> studentSchoolPreferencesArray) {

        List<Integer> result = new ArrayList<>(studentScoreArray);
        int seatsRemained = 0, studentsRemained = studentScoreArray.size();
        for (int seat : schoolSeatsArray)
            seatsRemained += seat;

        Map<Integer, List<Integer>> scoreToStudentsMap = new HashMap<>();
        buildScoreToStudents(studentScoreArray, scoreToStudentsMap);

        List<Integer> scores = new ArrayList<>();
        scores.addAll(scoreToStudentsMap.keySet());
        Collections.sort(scores, Collections.reverseOrder());

        for (Integer score : scores) {
            List<Integer> studentIds = scoreToStudentsMap.get(score);
            for (int studentId : studentIds) {
                List<Integer> schoolIds = studentSchoolPreferencesArray.get(studentId);
                for (int schoolId : schoolIds) {
                    int seatsAvailable = schoolSeatsArray.get(schoolId);
                    if (seatsAvailable > 0) {
                        schoolSeatsArray.set(schoolId, seatsAvailable - 1);
                        seatsRemained--;
                        studentId--;
                        break;
                    }
                }
            }
        }

        result.add(seatsRemained);
        result.add(studentsRemained);

        return result;
    }

    private static void buildScoreToStudents(List<Integer> studentScoreArray, Map<Integer, List<Integer>> scoreToStudents) {

        for (int studentId = 0; studentId < studentScoreArray.size(); studentId++) {
            int score = studentScoreArray.get(studentId);
            if (!scoreToStudents.containsKey(studentId)) {
                scoreToStudents.put(score, new ArrayList<>());
            }
            scoreToStudents.get(score).add(studentId);
        }

    }
}
