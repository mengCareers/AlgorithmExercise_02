package CompanyOriented.Pinterest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We have data with 3 properties: user_id, timestamp, action.
And we want to output a graph to visualize it aggregated by user_id.
We are asked to
1. define data structure and classes
2. construct the graph from log file
3. print out the graph similar to above

--1--
  --2--
    --3--
0 1 2 3 4
 */
public class BehaviorTree {

    public static void main(String[] args) {
        BehaviorTree inst = new BehaviorTree();
        inst.readLog();
    }

    Node root;
    Map<String, List<Character>> logInfo; // key: user_id, value: list of actions.

    public void readLog() {
        // read from log file
        try {
            logInfo = new HashMap<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splits = line.split(" ");
                String name = splits[0];
                Character action = splits[2].charAt(0);
                logInfo.putIfAbsent(name, new ArrayList<>());
                logInfo.get(name).add(action);
            }
            root = new Node();
            buildGraph();
            printGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildGraph() {
        for (List<Character> list : logInfo.values()) {
            insert(list);
        }
    }

    public void printGraph() { // DFS
        printGraphRecur(root, 1);
    }

    private void printGraphRecur(Node parentPtr, int level) {

        for (int i = 0; i < 26; i++) {
            if (parentPtr.children[i] != null) {
                for (int j = 0; j < level; j++) {
                    System.out.print("|---");
                }
                System.out.println((char) ('A' + i) + "(" + parentPtr.children[i].cnt + ")");
                printGraphRecur(parentPtr.children[i], level + 1);
            }
        }
        System.out.println();
    }

    private void insert(List<Character> actions) {
        Node ptr = root;
        for (char ch : actions) {
            int order = ch - 'A';
            if (ptr.children[order] == null) {
                ptr.children[order] = new Node();
            }
            ptr = ptr.children[order];
            ptr.cnt++;
        }
        ptr.isSequenceEnd = true;
    }

    class Node {
        Node[] children;
        boolean isSequenceEnd;
        int cnt;

        public Node() {
            children = new Node[26];
            isSequenceEnd = false;
            cnt = 0;
        }
    }
}
