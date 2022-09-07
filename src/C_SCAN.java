import java.util.ArrayList;
import java.util.Collections;

public class C_SCAN extends Scheduling{

    public C_SCAN(int start, int end, int headPointer, boolean Direction, ArrayList<Integer> queue) {
        super(start, end, headPointer,Direction,queue);//Start - End - HeadPointer
    }

    public void Calculate(){ //(start=0,16,24,43,50,82,140,170,190,End=199)
        queue.add(Start);
        queue.add(End);
        queue.add(HeadStart);
        Collections.sort(queue);
        int indexOfHead = queue.indexOf(HeadStart);
        int indexOfStart = queue.indexOf(Start);
        int indexOfEnd = queue.indexOf(End);

        //case Direction = Right
        if (Direction){
            //(start=0,16,24,43,50,82,140,170,190,End=199)
            for (int i = indexOfHead ; i<indexOfEnd ;) { //....,199
                TotalMovement += Math.abs(queue.get(i + 1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
                indexOfEnd--;
            }
            //(Start=0,16,24,43,End=199)
            if (queue.size()!=1){
                TotalMovement+= Math.abs(queue.get(indexOfStart) - queue.get(indexOfEnd)); //199-0
                sequences.add(queue.get(indexOfEnd));
            }
            queue.remove(queue.get(indexOfEnd));
            //(Start=0,16,24,43)
            for (int i=indexOfStart ;queue.size()!=1 && !queue.isEmpty();){
                TotalMovement+= Math.abs(queue.get(i+1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
            }
            if (!queue.isEmpty()) sequences.add(queue.get(0));
        }
        //case Direction = left
        else {//(Start = 0,16,24,43,50,82,140,170,190,199)
            for (int i = indexOfHead ; i>indexOfStart ;i--) {
                TotalMovement += Math.abs(queue.get(i - 1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
                indexOfEnd--;
            }
            //(Start=0,82,140,170,190,199)
            //indexOfStart = 0
            if (queue.size()!=1){
                TotalMovement+= Math.abs(queue.get(indexOfStart) - queue.get(indexOfEnd)); //199-0
                sequences.add(queue.get(indexOfStart));
            }
            queue.remove(queue.get(indexOfStart));
            indexOfEnd--;
            //(82,140,170,190,199)
            for (int i=indexOfEnd ;queue.size()!=1 && !queue.isEmpty();i--){//(82)
                TotalMovement+= Math.abs(queue.get(i-1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
            }
            if (!queue.isEmpty())sequences.add(queue.get(0));
        }
    }
}