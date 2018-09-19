package OS;

public class ReadyQueueMax {


    private OSProcess[] processes; // array simulation of min heap

    private int numberOfProcesses; // number of processes

    public ReadyQueueMax(int heapSize) {
        numberOfProcesses = 0;
        processes = new OSProcess[heapSize];
    }

    public OSProcess getHighestPriorityProcess() {
        if (isEmpty())
            return null;
        else
            return processes[0];
    }

    private void shiftDown(int nid) {
        int leftChildIndex = getLeftChildIndex(nid), rightChildIndex = getRightChildIndex(nid);
        int maxIndex; // smaller one of leftChildIndex and rightChildIndex
        OSProcess tmp;

        if (rightChildIndex >= numberOfProcesses) { // if without right child
            if (leftChildIndex >= numberOfProcesses) { // if without left child
                return;
            } else {
                maxIndex = leftChildIndex;
            }
        } else {
            if (processes[leftChildIndex].pPriority >= processes[rightChildIndex].pPriority) {
                maxIndex = leftChildIndex;
            } else
                maxIndex = rightChildIndex;
        }
        if (processes[nid].pPriority < processes[maxIndex].pPriority) {
            tmp = processes[maxIndex];
            processes[maxIndex] = processes[nid];
            processes[nid] = tmp;
            shiftDown(maxIndex);
        }
    }


    private void shiftUp(int nid) {
        int parentIndex;
        OSProcess tmp;
        if (nid != 0) {
            parentIndex = getParentIndex(nid);
            if (processes[parentIndex].pPriority < processes[nid].pPriority) {
                tmp = processes[parentIndex];
                processes[parentIndex] = processes[nid];
                processes[nid] = tmp;
                shiftUp(parentIndex);
            }
        }
    }

    public void remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("Heap is empty");
        } else {
            processes[0] = processes[numberOfProcesses - 1];
            numberOfProcesses--;
            if (numberOfProcesses > 0)
                shiftDown(0);
        }
    }

    public void insert(OSProcess process) throws Exception {
        if (numberOfProcesses == processes.length) {
            throw new Exception("Heap's underlying storage is overflow");
        } else {
            numberOfProcesses++;
            processes[numberOfProcesses - 1] = process;
            shiftUp(numberOfProcesses - 1);
        }
    }

    public boolean isEmpty() {
        return (numberOfProcesses == 0);
    }

    private int getParentIndex(int nid) {
        return (nid - 1) / 2;
    }

    private int getLeftChildIndex(int nid) {
        return 2 * nid + 1;
    }

    private int getRightChildIndex(int nid) {
        return 2 * nid + 2;
    }
}
