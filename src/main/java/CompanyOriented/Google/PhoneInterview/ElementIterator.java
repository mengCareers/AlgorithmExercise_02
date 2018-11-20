package CompanyOriented.Google.PhoneInterview;

/*
expand in constructor X
hasNextElement OK

even count
odd real element
 */
public class ElementIterator {

    public static void main(String[] args) throws Exception {
        int[] arr = {3, 9, 0, 1, 2, 3, 0, 9};
        ElementIterator inst = new ElementIterator(arr);
        while (inst.hasNextElement())
            System.out.println(inst.getNextElement());
    }

    int index; // Current index in array.
    int countCurr;
    int currElement;
    int[] array;

    public ElementIterator(int[] array) {
        if (array.length % 2 != 0)
            throw new IllegalArgumentException();
        index = 0;
        countCurr = 0;
        currElement = 0;
        this.array = array;
    }

    public boolean hasNextElement() {
        if (countCurr != 0)
            return true;
        if (index > array.length - 1)
            return false;
        // We assume number of elements in array is even (valid).
        countCurr = array[index];
        while (countCurr == 0) {
            index += 2;
            if (index > array.length - 1) {
                return false;
            }
            countCurr = array[index];
        }
        currElement = array[index + 1];
        index += 2;
        return true;
    }

    public int getNextElement() {
        countCurr--;
        return currElement;
    }
}
