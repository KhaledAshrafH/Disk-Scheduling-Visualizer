import java.util.ArrayList;
import java.util.Collections;

public class SCAN extends Scheduling {

    public SCAN(int start, int end, int headPointer, boolean Direction, ArrayList<Integer> queue) {
        super(start, end, headPointer,Direction,queue);//Start - End - HeadPointer
    }

    public void Calculate(){
        queue.add(HeadStart);
        //case Direction = Right
        if (Direction){
            queue.add(End);
            Collections.sort(queue);
            int indexOfHead = queue.indexOf(HeadStart);
            int indexOfEnd = queue.indexOf(End);
            for (int i = indexOfHead ; i<indexOfEnd ;){//(16,24,43,50,82,140,170,190,End=199)
                TotalMovement+= Math.abs(queue.get(i+1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
                indexOfEnd--;
            }
            //(16,24,43,End=199)
            for (int i=indexOfEnd ;queue.size()!=1;i--){
                TotalMovement+= Math.abs(queue.get(i-1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
            }
            sequences.add(queue.get(0));
        }
        //case Direction = false
        else {
            queue.add(Start);
            Collections.sort(queue);
            int indexOfHead = queue.indexOf(HeadStart);
            int indexOfStart = queue.indexOf(Start);
            for (int i = indexOfHead ; i>indexOfStart ;i--){//(Start = 0,16,24,43,50,82,140,170,190)
                TotalMovement+= Math.abs(queue.get(i-1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
            }
            //Start = 0,82,140,170,190
            for (int i=indexOfStart ;queue.size()!=1;){
                TotalMovement+= Math.abs(queue.get(i+1) - queue.get(i));
                sequences.add(queue.get(i));
                queue.remove(queue.get(i));
            }
            sequences.add(queue.get(0));
        }
    }
}