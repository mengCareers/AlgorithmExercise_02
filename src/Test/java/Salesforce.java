import CompanyOriented.Salesforce.ArrangeTheWords;
import CompanyOriented.Salesforce.CutSticks;
import CompanyOriented.Salesforce.IsPossible;
import CompanyOriented.Salesforce.KDiff;
import CompanyOriented.Salesforce.Node;
import CompanyOriented.Salesforce.RedundancyInALinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Salesforce {

    @Test
    public void testRedundancyInALinkedList() {
        Node node0 = new Node(3);
        Node node1 = new Node(4);
        Node node2 = new Node(4);
        Node node3 = new Node(3);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        RedundancyInALinkedList.distinct(node0);
        Node ptr = node0;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
    }

    @Test
    public void testCountKDiffPairs() {
        int[] nums = {1, 5, 3, 4, 2};
        System.out.println(KDiff.countKDiffPairs(nums));
    }

    @Test
    public void testIsPossible() {
        assertFalse(IsPossible.canConvert(1, 5, 3, 7));
        assertFalse(IsPossible.canConvert(1, 3, 3, 7));
    }

    @Test
    public void testCutSticks() {
        int[] lengths = {1, 1, 2, 3};
        assertArrayEquals(CutSticks.cutSticks(lengths), new int[]{4, 2, 1});
    }

    @Test
    public void testArrangeTheWords() {
        assertEquals(ArrangeTheWords.arrange("A."), "A.");
        assertEquals(ArrangeTheWords.arrange("Buy apple pen."), "Buy pen apple.");
        assertEquals(ArrangeTheWords.arrange("Long apple pen."), "Pen long apple.");
    }
}
