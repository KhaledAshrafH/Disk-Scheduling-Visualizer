import java.util.*;

public class Scheduling {
    Boolean Direction;//true = Right && false = Left
    public ArrayList<Integer> queue;//Input
    public Vector<Integer> sequences;//All Movements
    public int Start,End,HeadStart,TotalMovement;

    public Scheduling(int s,int e,int h,ArrayList<Integer> q) {
        Start =s;End=e;HeadStart=h;TotalMovement=0;
        queue = q;
        sequences = new Vector<>();
    }

    public Scheduling(int s,int e,ArrayList<Integer> q) {
        Start =s;End=e;TotalMovement=0;
        queue = q;
        sequences = new Vector<>();
    }

    public Scheduling(int s,int e,int h,Boolean d,ArrayList<Integer> q) {
        Direction = d;
        Start =s;End=e;HeadStart=h;TotalMovement=0;
        queue = q;
        sequences = new Vector<>();
    }

    public void display(){
        System.out.println("Total Movement = " + TotalMovement);
        System.out.print("Sequence = < ");
        for (Integer sequence : sequences) {
            System.out.print(sequence + " ");
        }
        System.out.println(">");
        System.out.println();
    }
}