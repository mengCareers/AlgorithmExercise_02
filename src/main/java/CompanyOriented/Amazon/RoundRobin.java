package CompanyOriented.Amazon;

import java.util.LinkedList;
import java.util.Queue;

/*
模拟round robin调度方法：
到达时间int[] Atime， 执行时间int[] Etime，
rule:
    一次只能处理一条。
    每次执行一个任务最多执行时间q，接着执行等待着的下一个任务。
    若前一个任务没执行完则放到队尾，等待下一次执行。
    最后返回值是float
output : avg waiting time to finish all processes
 */
/*
ds: queue
encapsulate entity
q : if Process.execTime > q, readd to queue
    before go on, add who already arrive to queue
+ should be variable curTime
+ if queue empty, (including initially)
curTime = Atime[index]
 */
public class RoundRobin {
    public float roundRobinScheduling(int[] Atime, int[] Etime, int q) {

        // corner cases to add

        int curTime = 0, idToProcess = 0, n = Atime.length, totalWaitTime = 0;
        Queue<Process> queue = new LinkedList<>();
        while (!queue.isEmpty() || idToProcess < n) {
            if (!queue.isEmpty()) {
                Process curProcess = queue.poll();
                totalWaitTime += curTime - curProcess.arrivalTime;
                curTime += Math.min(curProcess.execTime, q);
                for (; idToProcess < n && Atime[idToProcess] <= curTime; idToProcess++) {
                    queue.offer(new Process(Atime[idToProcess], Etime[idToProcess]));
                }
                if (curProcess.execTime > q) {
                    curProcess.execTime -= q;
                    queue.offer(curProcess);
                }

            } else {
                queue.offer(new Process(Atime[idToProcess], Etime[idToProcess]));
                curTime = Atime[idToProcess++];
            }
        }

        return (float) totalWaitTime / n;
    }

    class Process {
        int arrivalTime;
        int execTime;

        public Process(int arrivalTime, int execTime) {
            this.arrivalTime = arrivalTime;
            this.execTime = execTime;
        }
    }
}
