package Recursion;

public class CheckTwo {
    private String remove(String S) {
        StringBuilder sb = new StringBuilder(S);
        if (sb.indexOf("abc") != -1) {
            int index = sb.indexOf("abc");
            sb.delete(index, index + 3);
        }
        return sb.toString();
    }
}
