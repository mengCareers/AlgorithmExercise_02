package CompanyOriented.Amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
模拟Shortest Job First调度方法：
到达时间int[] Atime， 执行时间int[] Etime，
rule:
    一次只能处理一条。
    先执行持续时间短的那个，对于持续时间相等的，先执行最早到达处理器的。
output : avg waiting time to finish all processes
 */
public class ShortestJobFirst {
    public float ShortestJobFirst(int[] Atime, int[] Etime) {

        PriorityQueue<Process> pq = new PriorityQueue<>(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if (o1.execTime == o2.execTime) {
                    return o1.arrivalTime - o2.arrivalTime;
                }
                return o1.execTime - o2.execTime;
            }
        });

        int curTime = 0, totalWaitingTime = 0, index = 0, n = Atime.length;

        while (!pq.isEmpty() || index < n) {
            if (!pq.isEmpty()) {
                Process curProcess = pq.poll();
                totalWaitingTime = curTime - curProcess.arrivalTime;
                curTime += curProcess.execTime;
                for (; index < n && Atime[index] <= curTime; index++) {
                    pq.offer(new Process(Atime[index], Etime[index]));
                }
            } else {
                pq.offer(new Process(Atime[index], Etime[index]));
                curTime = Atime[index++];
            }
        }

        return (float) totalWaitingTime / n;
    }
}

class Process {
    int arrivalTime;
    int execTime;

    public Process(int arrivalTime, int execTime) {
        this.arrivalTime = arrivalTime;
        this.execTime = execTime;
    }
}
