package CompanyOriented.Google;

import java.util.*;

/*
It is intuitive to think of the solution that utilizes Set vals to check duplicates and List list to provide the random access of an element. The code below beats 20%:
Why is the solution above slow?
After list.remove((Integer)val);, all the succeeding elements need to be shifted to fill out the space created by removed element.
We can avoid that by swap val with the last element of the list (if val is not the last one), in this way, there is no other elements moving after the deletion.
Thus, we utilize Map valToLoc to store both the value and the location of an element (in addition, to check duplicates) , and List vals to provide the random access of an element.
The code below beats 80%:
* */
public class InsertDeleteGetRandomO1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.remove(1);
        System.out.println(list.get(1));
    }

    Map<Integer, Integer> valToLoc;
    List<Integer> vals;

    public InsertDeleteGetRandomO1() {
        vals = new ArrayList<>();
        valToLoc = new HashMap<>();
    }

    public boolean insert(int val) {
        if (valToLoc.containsKey(val)) {
            return false;
        }
        valToLoc.put(val, vals.size());
        vals.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valToLoc.containsKey(val)) {
            return false;
        }
        int loc = valToLoc.get(val);
        if (loc != vals.size() - 1) {
            int lastVal = vals.get(vals.size() - 1);
            valToLoc.put(lastVal, loc);
            vals.set(loc, lastVal);

        }
        vals.remove(vals.size() - 1);
        valToLoc.remove(val);
        return true;
    }

    public int getRandom() {
        Random rand = new Random();
        return vals.get(rand.nextInt(vals.size()));
    }
}
