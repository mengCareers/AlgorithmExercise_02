package CompanyOriented.Databricks;

/**
 * O(1) time complexity.
 */
public class TreeTraversalWithParentPointer {
    public static void preorderTraversal(Node root) {
        if (root == null)
            return;
        Node pre = null, cur = root;

        while (true) {
            if (pre == root && cur == null)
                break;
            if (pre == cur.parent || pre == null) {
                visit(cur);
                pre = cur;
                if (cur.left != null)
                    cur = cur.left;
                else if (cur.right != null)
                    cur = cur.right;
                else
                    cur = cur.parent;
            } else if (pre == cur.left) {
                pre = cur;
                cur = cur.right;
            } else if (pre == cur.right) {
                pre = cur;
                cur = cur.parent;
            }
        }
    }

    private static void visit(Node node) {
        System.out.println("Visit " + node.val);
    }


}

