package CompanyOriented.Amazon;

import java.util.ArrayList;

/*
avg = sum of node values / number of nodes
 */
/*
its total number of nodes (if it is as root) and sum of them can identify a state
state(num, sum) as avg of subtree when num of subnodes, and their sum
state transition: bottom - up
prev state(childrenNum, childrenSum)
cur state(num, sum)
get the biggest sum / num
 */
/* Divide and Conquer
the problem can be easier if we decrease it to subproblmes

base case:
    if there is one node without children, return val / 1
    if there is one node with children, sum(children sum) + val / sum(children num) + 1

DFS
start : val, 1
end when root == null or no children
 */
public class HighestAvgSubtree {
    double highestAvg;
    Node highestAvgRoot;

    public Node getHighestAvgSubtree(Node root) {

        if (root == null) {
            return root;
        }

        highestAvg = Double.MIN_VALUE;
        getHighestAvgSubtreeFrom(root);

        return highestAvgRoot;
    }

    public int[] getHighestAvgSubtreeFrom(Node root) {

        if (root == null || root.children.isEmpty()) {
            return new int[]{1, root.val};
        }

        int curSum = root.val, curNum = 1;
        for (Node tmp : root.children) {
            int[] tmpResult = getHighestAvgSubtreeFrom(tmp);
            curSum += tmpResult[1];
            curNum += tmpResult[0];
        }
        double avg = curSum / curNum;
        if (avg > highestAvg) {
            highestAvg = avg;
            highestAvgRoot = root;
        }

        return new int[]{curNum, curSum};
    }
}

class Node {
    int val;
    ArrayList<Node> children;

    public Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}
