package Test;

import java.util.ArrayList;
import java.util.List;

public class Callstack {
    public static void main(String[] args) {
        Callstack inst = new Callstack();
        List<Integer> list = new ArrayList<>();
        inst.func(1, list);
    }

    public void func(int i, List<Integer> resultList) {
        if (i > 3) {
            resultList.add(i);
            return;
        }
        func(i + 1, resultList);
        func(i + 2, resultList);
    }

    public void func(int i) {
        if (i > 3) {
            return;
        }
        func(i + 1);
    }

    public void funcElse(int i) {
        if (i > 3) {
            System.out.println("ok");
        } else {
            funcElse(i + 1);
        }
    }
}

