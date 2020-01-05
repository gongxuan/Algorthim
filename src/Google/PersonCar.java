package Google;
//http://www.1point3acres.com/bbs/thread-447853-1-1.html
//原题：一组坐标表示人，另一组表示车，车比人多，给每个人匹配最近的车。其中人和车的距离没有tie。

import java.util.Comparator;
import java.util.*;
import java.util.PriorityQueue;

public class PersonCar {
    public Pair[] match(int[] cars,int[] persons){
        PriorityQueue<Pair> minHeap  =new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                int distance1 = Math.abs(o1.car-o1.person);
                int distance2 = Math.abs(o2.car-o2.person);
                return distance1-distance2;
            }
        });
        int carLen = cars.length;
        int personsLen = persons.length;
        for(int i=0;i<carLen;i++){
            for(int j=0;j<personsLen;j++){
                minHeap.add(new Pair(cars[i],persons[i]));
            }
        }
        Pair[] res = new Pair[personsLen];
        int index = 0;
        HashSet<Integer> usedCars = new HashSet<>();
        HashSet<Integer> usedPersons = new HashSet<>();
        while(!minHeap.isEmpty()){
            Pair cur = minHeap.poll();
            if(!usedPersons.contains(cur.person) && !usedCars.contains(cur.car)){
                res[index++] = cur;
                usedPersons.add(cur.person);
                usedCars.add(cur.car);
            }
        }
        return res;
     }
    class Pair{
        int car;
        int person;
        public Pair(int c,int p){
            this.car = c;
            this.person = p;
        }
    }
}
