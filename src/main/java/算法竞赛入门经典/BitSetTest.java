package 算法竞赛入门经典;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        BitSetTest inst = new BitSetTest();
        inst.testBitSet();
    }

    public void testBitSet() {
        BitSet bs1 = new BitSet();
        BitSet bs2 = new BitSet();
        bs1.set(0);
        bs2.set(1);
        bs1.and(bs2);
        System.out.println(bs1);
    }
}
