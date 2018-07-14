package Facebook;

import Google.TreeNode;

import java.util.*;

/**
 * input: root
 * output: vertical order traversal of nodes' values
 * <p>
 * can we set column number, and according to that output?
 * how do we set column number
 * MENTOR: DURING TRAVERSAL, BFS OR DFS?
 * bfs,
 * MENTOR: if cur node col
 * left child col - 1, right child col + 1
 */
public class BTVerticalOrderTraversal {
    public static void main(String[] args) {
        BTVerticalOrderTraversal inst = new BTVerticalOrderTraversal();
        System.out.println(inst.verticalOrder(new TreeNode(1)));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> colNoToNodeValue = new HashMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        Queue<Integer> colNoQueue = new LinkedList<>();
        colNoQueue.offer(0);
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            int curColNo = colNoQueue.poll();
            maxVal = Math.max(curColNo, maxVal);
            minVal = Math.min(curColNo, minVal);
            if (!colNoToNodeValue.containsKey(curColNo)) {
                colNoToNodeValue.put(curColNo, new ArrayList<>());
            }
            colNoToNodeValue.get(curColNo).add(curNode.val);
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                colNoQueue.offer(curColNo - 1);
            }
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                colNoQueue.offer(curColNo + 1);
            }
        }
        for (int mapKey = minVal; mapKey <= maxVal; mapKey++) {
            result.add(colNoToNodeValue.get(mapKey));
        }
        return result;
    }

}
