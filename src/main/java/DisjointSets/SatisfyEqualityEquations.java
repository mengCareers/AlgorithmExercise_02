package DisjointSets;

import java.util.HashSet;
import java.util.Set;

public class SatisfyEqualityEquations {
    class UnionFind {
        int[] parent;

        public UnionFind() {
            parent = new int[26];
            for (int i = 0; i < 26; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) parent[rootX] = rootY;
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind();
        Set<String> equal = new HashSet<>(), inequal = new HashSet<>();
        for (String equation : equations) {
            if (equation.contains("==")) {
                equal.add(equation);
            } else {
                inequal.add(equation);
            }
        }
        for (String equation : equal) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            uf.union(a - 'a', b - 'a');
        }

        for (String equation : inequal) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            int rootX = uf.find(a - 'a');
            int rootY = uf.find(b - 'a');
            if (rootX == rootY) return false;
        }

        return true;
    }


    public static void main(String[] args) {
        SatisfyEqualityEquations inst = new SatisfyEqualityEquations();
        String[] equs = {"f==d", "b!=e", "d!=c", "b==c", "b!=a", "b!=f"};
        System.out.println(inst.equationsPossible(equs));
    }
}
/*
 * extract
 *
 * if ==, same connected component
 *
 * != cannot be same
 * */
