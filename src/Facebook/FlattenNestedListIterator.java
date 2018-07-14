package Facebook;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * implement an iterator to flatten nestedList
 */
public class FlattenNestedListIterator {
}

/**
 * When do we flatten?
 * When we next(), we make sure the next one is Integer
 * e.g.
 * 2, [1, 1]
 * next(), we return 2
 * 2
 * |
 */
class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger()) {
                return true;
            }
            NestedInteger tmp = stack.pop();
            List<NestedInteger> tmpList = tmp.getList();
            for (int i = tmpList.size() - 1; i >= 0; i--) {
                stack.push(tmpList.get(i));
            }
        }
        return false;
    }
}