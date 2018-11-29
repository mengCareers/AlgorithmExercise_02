package CompanyOriented.Google.Interview;

public class TreeToLinkedList {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private ListNode prev; // point to head initially

    public ListNode transferToList(TreeNode root) {

        ListNode dummyNode = new ListNode(0);
        prev = dummyNode;
        inorder(root);
        return dummyNode.next;
    }

    private void inorder(TreeNode node) {
        // Base case
        if (node == null)
            return;

        inorder(node.left);
        ListNode curr = new ListNode(node.val);
        prev.next = curr;
        prev = curr;
        inorder(node.right);
    }

}
