package CompanyOriented.Google;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator = null;
    Integer nextVal = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if (iterator.hasNext()) {
            nextVal = iterator.next();
        }
    }

    public Integer peek() {
        return nextVal;
    }

    @Override
    public Integer next() {
        Integer tmp = nextVal;
        nextVal = iterator.hasNext() ? iterator.next() : null;
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return nextVal != null;
    }
}
