package CompanyOriented.Google.PhoneInterview;

import CompanyOriented.Google.TreeNode;

import java.util.*;

/*
traverse and test each node
since tree node doest not have parent pointer
we recursive from bottoom-up
divide and conquer
basecase 1    0 node, null
basecase 2    1 node, return shouldBeErased(root) : null : root
case 3        2 nodes, check root, if root is erased. return root.left
        root
        /
       root.left
case 4
case 5        3 nodes, check root, if root is erased,return {check root.left, check root.right};
WHEN YOU THINK OF DFS IN TREE, THINK WHICH KIND OF DFS TRAVERSAL DO YOU WANT
post order a c e d b X
we need to pre order B A D C E, if B should be erased, we have to save A and D separately

 */
/*
All kinds of traversal aim to go through all nodes in a tree.
There are only two types of manipulation for one TreeNode
    if to be erased, add children to result set, remove it from set
    it not to be erased, do nothing
 */
public class TreeAfterErased {

    Scanner scanner;

    private boolean shouldBeErased(TreeNode node) {
        scanner = new Scanner(System.in);
        System.out.println("T if want to erase " + node.val);
        String answer = scanner.nextLine();
        return (answer.equals("T")) ? true : false;
    }

    public static void main(String[] args) {
        TreeAfterErased inst = new TreeAfterErased();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        inst.printTree(root);
        List<TreeNode> forest = inst.prune_01(root);
        for (TreeNode node : forest) {
            inst.printTree(node);
        }
    }

    Set<TreeNode> possibleRoot;

    public List<TreeNode> prune_01(TreeNode root) {

        possibleRoot = new HashSet<>();
        possibleRoot.add(root);
        preorderTraverse(root);

        List<TreeNode> result = new ArrayList<>();
        result.addAll(possibleRoot);

        return result;
    }

    private void preorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        if (shouldBeErased(root)) {
            possibleRoot.add(root.left);
            possibleRoot.add(root.right);
            possibleRoot.remove(root);
        }

        preorderTraverse(root.left);
        preorderTraverse(root.right);
    }


    public List<TreeNode> prune_00(TreeNode root) {

        List<TreeNode> forest = new ArrayList<>();
        List<TreeNode> nodesToEraseList = new ArrayList<>();
        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();// key: child; value: parent
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (shouldBeErased(curNode)) {
                nodesToEraseList.add(curNode);
            }
            if (curNode.left != null) {
                childParentMap.put(curNode.left, curNode);
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                childParentMap.put(curNode.right, curNode);
                queue.offer(curNode.right);
            }
        }

        TreeNode parent, node;

        for (int i = nodesToEraseList.size() - 1; i >= 0; i--) {
            node = nodesToEraseList.get(i);
            if (node == root) {
                if (node.left != null) {
                    forest.add(node.left);
                }
                if (node.right != null) {
                    forest.add(node.right);
                }
                continue;
            }
            parent = childParentMap.get(node);
            if (parent.left == node) {
                if (node.left != null) {
                    forest.add(node.left);
                }
                if (node.right != null) {
                    forest.add(node.right);
                }
                parent.left = null;
            }
            if (parent.right == node) {
                if (node.left != null) {
                    forest.add(node.left);
                }
                if (node.right != null) {
                    forest.add(node.right);
                }
                parent.right = null;
            }
        }
        return forest;
    }

    private void printTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + ", ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }
}
