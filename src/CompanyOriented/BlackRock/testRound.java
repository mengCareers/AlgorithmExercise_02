package CompanyOriented.BlackRock;

public class testRound {
    public static void main(String[] args) {
        double num = 1.555;
        double result = Math.round(num * 100) / 100.0;
        System.out.println(result);

        int intResult = (int) Math.round(num);
        System.out.println(intResult);
    }
}
