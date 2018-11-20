package Contest;

public class KokoEatingBananas {
    public static void main(String[] args) {
        KokoEatingBananas inst = new KokoEatingBananas();
        int[] piles = {30, 11, 23, 4, 20};
        inst.minEatingSpeed(piles, 6);
    }

    public int minEatingSpeed(int[] piles, int H) {

        int minPile = 1, maxPile = Integer.MIN_VALUE;
        for (int tmp : piles) {
            maxPile = Math.max(maxPile, tmp);
        }

        while (minPile <= maxPile) {
            int hourNeeded = 0;
            int i = minPile + (maxPile - minPile) / 2;
            for (int tmp : piles) {
                if (tmp <= i) {
                    hourNeeded++;
                } else if (tmp % i != 0) {
                    hourNeeded += tmp / i + 1;
                } else {
                    hourNeeded += tmp / i;
                }
            }
            if (hourNeeded <= H) {
                maxPile = i - 1;
            } else {
                minPile = i + 1;
            }
        }

        return minPile;
    }
    // return -1;
//        int sumPiles = 0;
//        for (int tmp : piles) {
//            sumPiles += tmp;
//        }
//
//        return sumPiles % H == 0 ? sumPiles / H : sumPiles / H + 1;
}

