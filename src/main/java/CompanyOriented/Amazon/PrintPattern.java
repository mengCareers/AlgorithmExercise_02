package CompanyOriented.Amazon;

public class PrintPattern {

    public static void main(String[] args) {
        printChars(4);
        printOnes(4);
    }

    public static void printChars(int row) {

        char ch;
        for (int i = 0; i < row; i++) {
            ch = 'a';
            for (int j = 0; j <= i; j++) {
                System.out.print(ch++);
            }
            System.out.println();
        }
    }

    public static void printOnes(int row) {

        int x = 1;
        for (int i = 1; i <= row; i++) {
            for (int j = i; j > 0; j--) {
                System.out.print(x + "" + x);
            }
            System.out.println();
        }
    }
}
