package OS;


public class Scheduling {
    /**
     * Driver method to simulate scheduling using the Ready Queue (max-heap).
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        ReadyQueueMax readyQueue = new ReadyQueueMax(4);

        // Simulate insertion of process.
        OSProcess[] input = new OSProcess[5]; // The ready queue will enlarged automatically if the underlying storage is overflow.
        input[0] = new OSProcess("p1", 0, 1, 2);
        input[1] = new OSProcess("p2", 1, 1, 3);
        input[2] = new OSProcess("p3", 2, 1, 4);
        input[3] = new OSProcess("p4", 3, 1, 1);
        input[4] = new OSProcess("p5", 4, 1, 0);
        for (OSProcess process : input)
            readyQueue.insert(process);
        OSProcess process;
        process = readyQueue.getHighestPriorityProcess();
        System.out.println("The process with the highest priority is " + process.getProcessName() + ", with priority = " + process.getProcessPriority());

        // Simulate removal of process.
        while (!readyQueue.isEmpty()) {
            process = readyQueue.remove();
            System.out.println("The process removed is " + process.getProcessName() + ", with priority = " + process.getProcessPriority());
        }

    }
}
