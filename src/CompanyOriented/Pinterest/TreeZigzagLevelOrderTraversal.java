package CompanyOriented.Pinterest;

import CompanyOriented.Google.TreeNode;

import java.util.*;

/*
From left to right, then right to left for the next level and alternate between.
 */
/*
Level order traversal, flag indicate left to right or right to left

pseudo
q.add(root)
while (q not empty) {
    sz = q.size
    for (i = 0.. sz)
        q.poll
        add q neighbours to list
        if (leftToRight) {
            add list to result
        }
        else {
            add reverse list to result
        }
        !leftToRight
}
 */
public class TreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(NaryTreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        // For BFS.
        Queue<NaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // For zigzag order traversal.
        boolean leftToRight = true;
        Stack<Integer> tempStack = new Stack<>();
        List<Integer> curResult;

        while (!queue.isEmpty()) {
            curResult = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NaryTreeNode node = queue.poll();
                if (leftToRight)
                    curResult.add(node.val);
                else
                    tempStack.add(node.val);
                queue.addAll(node.children);
            }
            while (!tempStack.isEmpty()) {
                curResult.add(tempStack.pop());
            }
            result.add(curResult);
            leftToRight = !leftToRight;
        }

        return result;
    }

}

class NaryTreeNode {
    int val;
    List<NaryTreeNode> children;

    public NaryTreeNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}