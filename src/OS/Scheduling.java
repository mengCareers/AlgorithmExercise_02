package OS;

public class Scheduling {
    public static void main(String[] args) throws Exception {
        ReadyQueue readyQueue = new ReadyQueue(100);
        OSProcess p1 = new OSProcess("p1", 1, 1, 0);
        readyQueue.insert(p1);
    }
}
