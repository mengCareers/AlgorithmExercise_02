package 编程之美;

/**
 * input N
 * output minimum M such that N * M only contain 0 and 1
 * 谁更有明显特征，N*M，所以我们搜索N*M而不是M -- 缩小搜索空间
 * 问题变成，output minimum X that X only contain 0 and 1 && X % N == 0
 */
public class FindZeroOneInteger {
    public static void main(String[] args) {
        FindZeroOneInteger inst = new FindZeroOneInteger();
        inst.findInteger(99);
    }

    public String findInteger(int N) {
        int i = 1;
        while (true) {
            long curNum = Long.valueOf(Integer.toBinaryString(i));
            if (curNum % N == 0) {
                System.out.println("N * " + curNum / N + " = " + curNum);
                return String.valueOf(curNum / N);
            }
            i++;
        }
    }
}
