package OS;


public class OSProcess {

    public enum ProcessStates {New, Ready, Running, Wait, Terminate}

    private String processName;
    private long processId;
    private int processState;
    private int processPriority;

    public OSProcess(String processName, long processId, int processState, int processPriority) {
        this.processName = processName;
        this.processId = processId;
        this.processState = processState;
        this.processPriority = processPriority;
    }

    public String getProcessName() {
        return processName;
    }

    public long getProcessId() {
        return processId;
    }

    public int getProcessState() {
        return processState;
    }

    public int getProcessPriority() {
        return processPriority;
    }

    public void setProcessState(int processState) {
        this.processState = processState;
    }

    public void setProcessPriority(int processPriority) {
        this.processPriority = processPriority;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

//    public void setProcessId(long processId) {
//        this.processId = processId;
//    }
}
