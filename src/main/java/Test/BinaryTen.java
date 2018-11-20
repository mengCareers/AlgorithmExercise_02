package Test;

public class BinaryTen {
    public static void main(String[] args) {
        printBinaryOf(40320);
    }

    public static void printBinaryOf(int N) {
        System.out.println(N + " - " + Integer.toBinaryString(N));
    }
}
