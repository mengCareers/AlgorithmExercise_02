package CompanyOriented.Pinterest;

public class RangeSum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(nums);

        SegmentTree segmentTree = new SegmentTree(nums);

    }
}

class BinaryIndexedTree {

    private static int[] e;
    private static int[] nums;

    public BinaryIndexedTree(int[] nums) {
        e = new int[nums.length + 1];
        BinaryIndexedTree.nums = nums;
        buildBIT();
    }

    /*
    Range sum from nums[start - 1] to nums[end - 1]
     */
    public int rangeSumFromTo(int start, int end) {
        return rangeSumFromStart(end) - rangeSumFromStart(start - 1);
    }

    private void buildBIT() {
        for (int i = 0; i < nums.length; i++)
            updateBIT(i + 1, nums[i]);
    }

    private static int rightmostOneUtility(int num) {
        return num & (~(num - 1));
    }

    private int rangeSumFromStart(int i) {
        int sum = 0;
        while (i > 0) {
            sum += e[i];
            i = i - rightmostOneUtility(i);
        }
        return sum;
    }

    private void updateBIT(int i, int value) {
        while (i < e.length) {
            e[i] += value;
            i = i + rightmostOneUtility(i);
        }
    }
}

class SegmentTree {
    private static int[] sumSegmentTree;
    private static int[] nums;

    public SegmentTree(int[] nums) {
        int maxSize = 1000; // Number of nodes in tree.
        sumSegmentTree = new int[maxSize];
        SegmentTree.nums = nums;
        buildSegmentTree(1, 0, nums.length - 1);
    }

    private void buildSegmentTree(int segmentId, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            sumSegmentTree[segmentId] = nums[startIndex];
        } else {
            buildSegmentTree(2 * segmentId, startIndex, getMidUtility(startIndex, endIndex));
            buildSegmentTree(2 * segmentId + 1, getMidUtility(startIndex, endIndex) + 1, endIndex);
            sumSegmentTree[segmentId] = sumSegmentTree[2 * segmentId] + sumSegmentTree[2 * segmentId + 1];
        }
    }

    /*
    Range sum from nums[startIndex] to nums[endIndex]
     */
    public int getSum(int startIndex, int endIndex) {
        return getSumRecur(1, 0, nums.length - 1, startIndex, endIndex);
    }

    private int getSumRecur(int segmentId, int segmentStart, int segmentEnd, int startIndex, int endIndex) {

        if (startIndex <= segmentStart && segmentEnd <= endIndex) {
            return sumSegmentTree[segmentId];
        }

        if (segmentEnd < startIndex || segmentStart > endIndex) {
            return 0;
        }

        int leftResult = getSumRecur(2 * segmentId, segmentStart, getMidUtility(segmentStart, segmentEnd), startIndex, endIndex);
        int rightResult = getSumRecur(2 * segmentId + 1, getMidUtility(segmentStart, segmentEnd) + 1, segmentEnd, startIndex, endIndex);

        return leftResult + rightResult;
    }


    public void updateSegmentTree(int index, int val) {
        updateSegmentTreeRecur(index, val, 1, 0, nums.length - 1);
    }

    private void updateSegmentTreeRecur(int index, int val, int segmentId, int startIndex, int endIndex) {
        if (index == startIndex && index == endIndex) {
            sumSegmentTree[segmentId] = val;
        } else {
            int midIndex = getMidUtility(startIndex, endIndex);
            if (index >= startIndex && index <= midIndex)
                updateSegmentTreeRecur(index, val, 2 * segmentId, startIndex, midIndex);
            else if (index >= midIndex + 1 && index <= endIndex)
                updateSegmentTreeRecur(index, val, 2 * segmentId + 1, midIndex + 1, endIndex);
        }
    }


    private static int getMidUtility(int startIndex, int endIndex) {
        return startIndex + ((endIndex - startIndex) >> 1);
    }
}
