package CompanyOriented.Google;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list
 * -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * <p>
 * Example 2:
 * Given the list [1,[4,[6]]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
public class FlattenNestedListIterator {
//    Stack<NestedInteger> stack;
//
//    public NestedIterator(List<NestedInteger> nestedList) {
//        stack = new Stack<>();
//        for (int i = nestedList.size() - 1; i >= 0; i--) {
//            stack.push(nestedList.get(i));
//        }
//    }
//
//    @Override
//    public Integer next() {
//        return stack.pop().getInteger();
//    }
//
//    @Override
//    public boolean hasNext() {
//        while (!stack.isEmpty()) {
//            if (stack.peek().isInteger()) {
//                return true;
//            }
//            List<NestedInteger> tmpList = stack.pop().getList();
//            for (int i = tmpList.size() - 1; i >= 0; i--) {
//                stack.push(tmpList.get(i));
//            }
//        }
//        return false;
//    }
}