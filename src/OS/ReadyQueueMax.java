package OS;

public class ReadyQueueMax {

    private OSProcess[] processes; // Array simulation of the max-heap.
    private int numProcesses; // Number of processes currently

    public ReadyQueueMax(int maxSize) {
        processes = new OSProcess[maxSize];
        numProcesses = 0;
    }

    /**
     * Get the process at top of heap - with the highest priority
     *
     * @return the process with the highest priority
     */
    public OSProcess getHighestPriorityProcess() {
        return (isEmpty()) ? null : processes[0];
    }

    /**
     * To remove a process from the Ready Queue.
     *
     * @return The process removed.
     * @throws Exception When the Ready Queue is empty.
     */
    public OSProcess remove() throws Exception {

        if (isEmpty())
            throw new Exception("The Ready Queue is empty");

        // Move the last process of heap to the top and sift it down into its proper position.
        OSProcess toRemove = processes[0];

        processes[0] = processes[numProcesses - 1];
        numProcesses--;
        siftDown(0);

        return toRemove;
    }

    /**
     * To insert a new process to the Ready Queue.
     *
     * @param process The new process.
     */
    public void insert(OSProcess process) {

        if (numProcesses == processes.length) {
            // Either enlarge the heap or throw Expception when the storage of heap is overflow.
            OSProcess[] newProcesses = new OSProcess[processes.length * 2];
            System.arraycopy(processes, 0, newProcesses, 0, numProcesses);
            processes = newProcesses;

            //throw new Exception("The underlying storage of the Ready Queue is overflow");
        }
        // Add the specified process to the heap and sift it up into its proper position.
        processes[numProcesses] = process;
        siftUp(numProcesses);
        numProcesses++;
    }

    /**
     * Sift processes[processIndex] down into its proper position.
     *
     * @param processIndex
     */
    private void siftDown(int processIndex) {

        OSProcess toSift = processes[processIndex];

        // Find where toSift belongs.
        int parentIndex = processIndex;
        int childIndex = 2 * processIndex + 1;
        while (childIndex < numProcesses) {
            if (childIndex < numProcesses - 1 &&
                    processes[childIndex].getProcessPriority() < processes[childIndex + 1].getProcessPriority())
                childIndex = childIndex + 1;
            if (toSift.getProcessPriority() >= processes[childIndex].getProcessPriority())
                break;
            // Move processes[childIndex] up and move down one level in the tree.
            processes[parentIndex] = processes[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }

        processes[parentIndex] = toSift;
    }

    /**
     * Sift processes[processIndex] up into its proper position.
     *
     * @param processIndex
     */
    private void siftUp(int processIndex) {

        OSProcess toSift = processes[processIndex];

        // Find where toSift belongs.
        int childIndex = processIndex;
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;
            if (toSift.getProcessPriority() <= processes[parentIndex].getProcessPriority())
                break;
            // Move processes[parentIndex] down and move up one level in the tree.
            processes[childIndex] = processes[parentIndex];
            childIndex = parentIndex;
        }

        processes[childIndex] = toSift;
    }

    /**
     * Check if the Ready Queue is empty now.
     *
     * @return true if the heap currently has no items.
     */
    public boolean isEmpty() {
        return (numProcesses == 0);
    }

}
