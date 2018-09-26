package CompanyOriented.GoldmanSachs;

/*
perfect team contains exactly five members: p c m b z
given skills, count perfect teams
 */
public class PerfectTeams {
    static int countTeams(String skills) {

        int[] skill_count = new int[26];
        boolean[] isModified = new boolean[26];
        char[] array = skills.toCharArray();
        for (char ch : array) {
            skill_count[ch - 'a']++;
            isModified[ch - 'a'] = true;
        }
        int minCnt = Integer.MAX_VALUE;
        for (int i = 0; i < skill_count.length; i++) {
            if (isModified[i]) {
                minCnt = Math.min(minCnt, skill_count[i]);
            }
        }

        return minCnt;
    }
}
