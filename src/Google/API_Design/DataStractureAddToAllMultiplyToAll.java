package Google.API_Design;

import java.util.*;

public class DataStractureAddToAllMultiplyToAll {
    //a(x+b) = ax +ab
    List<Double> list  = new ArrayList<>();
    private static double ADD;
    private static double MULTI;   //multiply first
    public DataStractureAddToAllMultiplyToAll(){
        ADD = 0;
        MULTI = 1;
    }
    public void append(int val){
        list.add( (val-ADD)/MULTI );
    }
    public void addToAll(int val){
        ADD += val;
    }
    public void multipleToAll(int val){
        MULTI *= val;
        ADD *= val;
    }
    public int get(int id){
        if(id>=list.size()){
            return -1;
        }
        return (int)(list.get(id)*MULTI + ADD);
    }
    public static void main(String[] arg){
        DataStractureAddToAllMultiplyToAll d = new DataStractureAddToAllMultiplyToAll();
        d.append(1);
        d.addToAll(1);
        d.multipleToAll(3);
        System.out.println(d.get(0));
        d.addToAll(2);
        d.append(10);
        d.multipleToAll(10);
        System.out.println(d.get(0));
        System.out.println(d.get(1));
    }
}

