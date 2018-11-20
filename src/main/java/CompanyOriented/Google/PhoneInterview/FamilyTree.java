package CompanyOriented.Google.PhoneInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
input: n-ary tree
output: impl birth(), printInheritOrder()
oldest child inherit
if oldest die, pass to oldest's oldest child

printOrder - left subtree in front of right subtree - preorder

death - del node fake - boolean to indicate status
birth - insert node (with parent string, we need to access to node, a map needed)
 */

interface Monocracy {
    void birth(String child, String parent);

    void death(String name);

    List<String> getOrderOfSuccession();
}

public class FamilyTree implements Monocracy {

    /* Key is string, value is node. */
    Map<String, Node> strToNode;
    Node root;

    public FamilyTree(String king) {
        root = new Node(king);
        strToNode = new HashMap<>();
        strToNode.put(king, root);
    }

    @Override
    public void birth(String child, String parent) {
        validateNodeString(parent);

        Node childNode = new Node(child);
        strToNode.put(child, childNode);

        Node parentNode = strToNode.get(parent);
        parentNode.children.add(childNode);
    }

    @Override
    public void death(String name) {
        validateNodeString(name);
        strToNode.get(name).isAlive = false;
    }

    @Override
    public List<String> getOrderOfSuccession() {
        List<String> order = new ArrayList<>();
        dfs(root, order);
        return order;
    }

    private void dfs(Node node, List<String> order) {
        if (node == null) {
            return;
        }
        if (node.isAlive)
            order.add(node.val);
        for (Node neighbour : node.children) {
            dfs(neighbour, order);
        }
    }

    private void validateNodeString(String str) {
        if (!strToNode.containsKey(str))
            throw new IllegalArgumentException();
    }

    class Node {
        List<Node> children;
        String val;
        boolean isAlive;

        public Node(String val) {
            this.val = val;
            children = new ArrayList<>();
            isAlive = true;
        }
    }
}

