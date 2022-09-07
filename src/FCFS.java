import java.util.ArrayList;

public class FCFS extends Scheduling{

    public FCFS(int Start, int End, int HeadPointer, ArrayList<Integer> queue) {
        super(Start, End, HeadPointer, queue);
    }

    public void Calculate(){
        for (int current : queue) {
            TotalMovement += Math.abs(current - HeadStart);
            HeadStart = current;
        }
        sequences.addAll(queue);
    }
}