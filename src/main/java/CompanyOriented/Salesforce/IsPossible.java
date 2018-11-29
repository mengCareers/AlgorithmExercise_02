package CompanyOriented.Salesforce;

/**
 * input: int[] pair, int[] target
 * output: true if pair can be converted to target by (x, y) => (x, x + y) or (x + y, x)
 */
public class IsPossible {
    public static boolean canConvert(int a, int b, int targetA, int targetB) {
        while (targetA > a && targetB > b) {
            if (targetA < targetB) {
                targetB -= targetA;
            } else {
                targetA -= targetB;
            }
            if (targetA == a && targetB == b)
                return true;
        }

        return false;
    }
}
/*
(a, b) -> (a, c = b+a)
b = c - a
(a, b) -> (c = a+b, b)
        a = c - b
(a, c) || (c, b)
3, 7  7, 3
3, 4  4, 3
3, 1  1, 3
 */