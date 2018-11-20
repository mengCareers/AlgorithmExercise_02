package DFS;

import java.util.List;

class NestedInteger {

    public boolean isInteger() {
        return true;
    }

    public List<NestedInteger> getList() {
        return null;
    }

    public int getInteger() {
        return 1;
    }
}

public class NestedListWeightSumII {
    int maxDepth, depthSum;

    public int depthSumInverse(List<NestedInteger> nestedList) {

        maxDepth = 0;
        depthSum = 0;

        getMaxDepth(nestedList, 1);
        getDepthSum(nestedList, maxDepth);

        return depthSum;
    }

    private void getMaxDepth(List<NestedInteger> nestedList, int curDepth) {

        for (NestedInteger tmp : nestedList) {
            if (!tmp.isInteger()) {
                getMaxDepth(tmp.getList(), curDepth + 1);
            }
        }

        maxDepth = Math.max(maxDepth, curDepth);
    }

    private void getDepthSum(List<NestedInteger> nestedList, int curDepth) {

        if (curDepth == 0) {
            return;
        }

        for (NestedInteger tmp : nestedList) {
            if (tmp.isInteger())
                depthSum += tmp.getInteger() * curDepth;
            else {
                getDepthSum(tmp.getList(), curDepth - 1);
            }
        }
    }
}
