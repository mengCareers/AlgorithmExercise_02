package CompanyOriented.Facebook;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}

/*
both values and pointers need to be cloned
so DFS is better
 */
public class CloneGraph {
    Map<Integer, UndirectedGraphNode> memo;

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        memo = new HashMap<>();
        return cloneFrom(node);
    }

    private UndirectedGraphNode cloneFrom(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        if (memo.containsKey(node.label)) {
            return memo.get(node.label);
        }
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        memo.put(node.label, newNode);
        for (UndirectedGraphNode neighbour : node.neighbors) {
            newNode.neighbors.add(cloneFrom(neighbour));
        }
        return newNode;
    }
}


