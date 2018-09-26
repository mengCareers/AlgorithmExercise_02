package CompanyOriented.Visa;

public class FindTheWinner {
    public static void main(String[] args) {
        FindTheWinner inst = new FindTheWinner();
        int[] andrea = {1, 2, 3};
        int[] maria = {2, 1, 3};
        String ans = inst.findWinner(andrea, maria, "Even");
        System.out.println(ans);
    }

    public String findWinner(int[] andrea, int[] maria, String s) {
        int n = andrea.length, start = (s.equals("Even")) ? 0 : 1;// assume two array same
        int scoreA = 0, scoreM = 0;
        for (int i = 0; i < n; i += 2) {
            int numA = andrea[i];
            int numM = maria[i];
            scoreA += numA - numM;
            scoreM += numM - numA;
        }
        if (scoreA > scoreM)
            return "Andrea";
        if (scoreA == scoreM)
            return "Tie";
        return "Maria";
    }
}
