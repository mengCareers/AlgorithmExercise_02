package OS;


public class OSProcess {
    public enum ProcessStates {New, Ready, Running, Wait, Terminate}

    public String pName; // process name
    public long pID; // process id
    public int pState; // process state
    public int pPriority; // process priority

    // customized
    public int pMemorySize;// size of process memory
    public int pMemoryStart; // start of process memory

    public OSProcess(String pName, long pID, int pState, int pPriority) {
        this.pName = pName;
        this.pID = pID;
        this.pState = pState;
        this.pPriority = pPriority;
    }
}
