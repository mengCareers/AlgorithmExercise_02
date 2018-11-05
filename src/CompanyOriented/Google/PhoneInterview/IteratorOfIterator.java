package CompanyOriented.Google.PhoneInterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
Implement an Iterator of Iterators which traverses through n iterators.
IE, 3 iterators: L1 = a1, a2, a3 L2 = b1, b2, b3 L3 = c1, c2, c3
Then the iterator should process them in this order: a1, b1, c1, a2, b2, c2, a3, b3, c3
 */
public class IteratorOfIterator implements Iterator {

    List<Iterator> iterators;
    int flag; // Index of current iterator in iterators.
    int numIterators; // Total number of iterators.

    public IteratorOfIterator(List<Iterator> iterators) {
        flag = 0;
        numIterators = iterators.size();
        this.iterators = iterators;
    }

    @Override
    public Integer next() {
        Iterator<Integer> iterator = iterators.get(flag);
        int value = iterator.next();
        flag++;
        if (flag == numIterators)
            flag = 0;
        return value;
    }

    @Override
    public boolean hasNext() {
        int currFlag = flag;
        while (!iterators.get(flag).hasNext()) {
            flag++;
            if (flag == numIterators)
                flag = 0;
            if (flag == currFlag)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        List<Iterator> iterators = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(5));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(9));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(13));
        IteratorOfIterator inst = new IteratorOfIterator(new ArrayList<>(Arrays.asList(l1.iterator(), l2.iterator(), l3.iterator())));
        while (inst.hasNext())
            System.out.println(inst.next());
    }
}
/*
set flag indicate next iterator, when next(), it will increase by one
 */