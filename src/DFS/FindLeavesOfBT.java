package DFS;

import CompanyOriented.Google.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeavesOfBT {
    Map<Integer, List<Integer>> heightToNodes;
    int maxheight;

    public List<List<Integer>> findLeaves(TreeNode root) {

        maxheight = 0;
        heightToNodes = new HashMap<>();
        getHeight(root);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= maxheight; i++) {
            result.add(heightToNodes.get(i));
        }

        return result;
    }

    private int getHeight(TreeNode cur) {

        if (cur == null) {
            return 0;
        }

        int curHeight = Math.max(getHeight(cur.left), getHeight(cur.right)) + 1;
        if (!heightToNodes.containsKey(curHeight)) {
            heightToNodes.put(curHeight, new ArrayList<>());
        }
        heightToNodes.get(curHeight).add(cur.val);
        maxheight = Math.max(maxheight, curHeight);

        return curHeight;
    }

}
