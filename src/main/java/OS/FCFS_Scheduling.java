package OS;

public class FCFS_Scheduling {

    public void calculateWaitingTime(int n, int[] bt, int[] wt) {
        wt[0] = 0;
        for (int i = 0; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    public void calculateTurnAroundTime(int n, int[] bt, int[] wt, int[] tat) {
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }
}
