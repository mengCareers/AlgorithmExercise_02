package CompanyOriented.Nordstrom;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sorting {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        String line = "1.0 -1.0 2.3 3.9 0.5"; // 1.0 -1.0 2.3 3.9 0.5
//        while ((line = scanner.nextLine()) != null) {
        String[] strNums = line.split(" ");
        double[] nums = Arrays.stream(strNums)
                .mapToDouble(Double::parseDouble)
                .toArray();
        heapSort(nums);
        printArray(nums);
//        }
    }

    private static void printArray(double[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + " ");
//        }
        System.out.println(Arrays.stream(nums).mapToObj(Double::toString).collect(Collectors.joining(" ")));
    }

    private static void heapSort(double[] nums) {

        int n = nums.length;

        /* Build the heap for root is the largest. */
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(nums, i, n);

        /* Remove element from heap one by one. */
        for (int i = n - 1; i >= 0; i--) {
            double temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapify(nums, 0, i);
        }
    }

    private static void heapify(double[] nums, int root, int size) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size && nums[left] > nums[largest])
            largest = left;
        if (right < size && nums[right] > nums[largest])
            largest = right;
        if (largest != root) {
            double temp = nums[root];
            nums[root] = nums[largest];
            nums[largest] = temp;

            heapify(nums, largest, size);
        }
    }
}
