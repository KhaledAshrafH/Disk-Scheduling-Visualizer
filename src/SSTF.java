import java.util.ArrayList;

public class SSTF extends Scheduling{

     static class Visit {
        int time = 0;
        boolean mark = false;
    }

    public SSTF(int start, int end, int headPointer, ArrayList<Integer> queue) {
        super(start, end, headPointer, queue);
    }

    public void difference(ArrayList<Integer> q, int head, Visit[] diff) {
        for (int i = 0; i < diff.length; i++)
            diff[i].time = Math.abs(q.get(i) - head);
    }

    public int mini(Visit[] diff) {
        int nearest = -1, minimum = Integer.MAX_VALUE;
        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].mark && minimum > diff[i].time) {
                minimum = diff[i].time;
                nearest = i;
            }
        }
        return nearest;
    }

    public void Calculate() {
        Visit[] diff = new Visit[queue.size()];
        for (int i = 0; i < diff.length; i++) diff[i] = new Visit();
        int[] VisitedReq = new int[queue.size() + 1];
        for (int i = 0; i < queue.size(); i++) {
            VisitedReq[i] = HeadStart;
            difference(queue, HeadStart, diff);
            int index = mini(diff);
            diff[index].mark = true;
            TotalMovement += diff[index].time;
            HeadStart = queue.get(index);
        }
        VisitedReq[VisitedReq.length - 1] = HeadStart;
        for (int j : VisitedReq) {
            sequences.add(j);
        }
    }
}