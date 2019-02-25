import CompanyOriented.Databricks.IteratorOfIterators;
import CompanyOriented.Databricks.KInfront;
import CompanyOriented.Databricks.Node;
import CompanyOriented.Databricks.TreeTraversalWithParentPointer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Databricks {

    @Test
    public void testIteratorOfIterators() {
        
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 4, 7));
        Iterator<Integer> it1 = l1.iterator();
        List<Integer> l2 = new ArrayList<>(Arrays.asList(2, 5, 8));
        Iterator<Integer> it2 = l2.iterator();
        List<Integer> l3 = new ArrayList<>(Arrays.asList(3, 6, 9));
        Iterator<Integer> it3 = l3.iterator();
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));
        IteratorOfIterators<Integer> iter = new IteratorOfIterators<>(iteratorList);
        while (iter.hasNext())
            System.out.println(iter.next());
    }

    @Test
    public void testKInFront() {
        int[] nums = {1, 3, 2, 5, 4};
        assertArrayEquals(new int[]{0, 1, 1, 2, 2}, KInfront.findMinElements(nums, 2));
    }

    @Test
    public void testWindowSliding() {
        int[] nums = {1, 3, 2, 5, 4};
        KInfront.windowSliding(nums, 2);
        assertArrayEquals(new int[]{0, 1, 1, 2, 2}, KInfront.windowSliding(nums, 2));
    }

    @Test
    public void testTreeTraversalWithParentPointer() {
        Node root = buildTreeWithParentPointer();

        TreeTraversalWithParentPointer.preorderTraversal(root);
    }

    private Node buildTreeWithParentPointer() {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        buildConnection(root, node2, node3);
        buildConnection(node2, node4, node5);
        buildConnection(node3, node9, node10);
        buildConnection(node5, node6, node7);
        buildConnection(node7, null, node8);


        return root;
    }

    private void buildConnection(Node parent, Node leftChild, Node rightChild) {
        if (leftChild != null) {
            leftChild.parent = parent;
            parent.left = leftChild;
        }
        if (rightChild != null) {
            rightChild.parent = parent;
            parent.right = rightChild;
        }
    }
}

