package CompanyOriented.Databricks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @param <T> the data type of each list elements
 */
public class IteratorOfIterators<T> implements Iterator {
    private Iterator<Iterator<T>> iter;
    private List<T> list;
    private Iterator<T> listIter;

    public IteratorOfIterators(List<Iterator<T>> iteratorList) {
        iter = iteratorList.iterator();
        list = new ArrayList<T>();

        while (iter.hasNext()) {
            Iterator<T> cur = iter.next();
            if (!cur.hasNext())
                iter.remove();
            else
                list.add(cur.next());
            if (!iter.hasNext())
                iter = iteratorList.iterator();
        }

        listIter = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return listIter.hasNext();
    }

    @Override
    public T next() {
        return listIter.next();
    }
}
