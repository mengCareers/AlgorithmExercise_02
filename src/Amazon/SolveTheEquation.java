package Amazon;

/**
 * input: + / - / num
 * output: val of x in the form of x=..
 * x = 2
 */
public class SolveTheEquation {
    public String solveEquation(String equation) {

        String[] parts = equation.split("=");
        String leftPart = parts[0], rightPart = parts[1];
        int[] leftVals = evaluate(leftPart), rightVals = evaluate(rightPart);
        int cntX = leftVals[0] - rightVals[0]; // 1
        int cntNum = leftVals[1] - rightVals[1]; // -2
        if (cntX == 0) {
            if (cntNum != 0)
                return "No solution";
            return "Infinite solutions";
        }
        int valX = (-cntNum) / cntX;
        StringBuilder result = new StringBuilder();
        result.append("x=").append(valX);

        return result.toString();
    }

    private int[] evaluate(String exp) {

        int[] result = new int[2];
        String[] expElements = exp.split("(?=[-+])");

        for (String ele : expElements) {
            if (ele.equals("+x") || ele.equals("x")) {
                result[0]++;
            } else if (ele.equals("-x")) {
                result[0]--;
            } else if (ele.contains("x")) {
                result[0] += Integer.valueOf(ele.substring(0, ele.indexOf("x")));
            } else {
                result[1] += Integer.valueOf(ele);
            }
        }

        return result;
    }

}
