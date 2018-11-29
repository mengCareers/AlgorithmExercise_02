package CompanyOriented.Google.Interview;

import CompanyOriented.Google.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * input: n-ary tree, list of nodes to delete
 * output: list of nodes not deleted
 */
public class ListUndeletedNodes {

    public List<TreeNode> listNodes(TreeNode root, List<TreeNode> toDelete) {
        if (root == null || toDelete == null)
            return new ArrayList<>();

        List<TreeNode> nodeList = new ArrayList<>();
        Set<TreeNode> toDeleteSet = new HashSet<>(toDelete);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (!toDeleteSet.contains(curNode)) {
                nodeList.add(curNode);
            } else {
                if (curNode.left != null)
                    queue.offer(curNode.left);
                if (curNode.right != null)
                    queue.offer(curNode.right);
            }
        }
        return nodeList;
    }
}
/*
BFS
init q
q add root
while (q not empty)
    node = q.poll
    if toDelete not contains
        add to List
    if (node.left not null)
        q.add node.left
    if (node.right not null)
        q.add node.right
 */
