package Google.SweepLine;

import java.util.*;

public class TrafficSpeed {
// 中国小哥，直接上题目，说模拟Google Map中一个一维路段的车流，最后设计并表示出所有车流的平均速度，
// 比如有[0,14,90mph]表示有一个车流从位置0到14，速度为90mph，然后有[3,15,80mph]，那相当于分成了3个车流，
// [0,3,90mph]，[3,14,85mph]，[14,15,80mph]，考虑到有overlap要算平均值，所有这个车流的class设计的时候得加个count表示由多少车流汇合。
public static void main(String[] args) {
  //merge intervals if speed is same?
  TrafficSpeed ins = new TrafficSpeed();
  int[][] cars = {{0,14,90},{3,15,80}};
  List<Interval> res = ins.avgSpeed(cars);
  print(res);
}
  public List<Interval> avgSpeed(int[][] cars){
    int totalCars = 0;
    int totalSpeed = 0;
    List<Node> list = generateCarList(cars);
    Collections.sort(list,(c1,c2)->c1.pos!=c2.pos?c1.pos-c2.pos:!c1.isLeft?-1:1);
    int prevPos = Integer.MAX_VALUE;
    List<Interval> res = new ArrayList<>();
    for(Node cur : list){
      if(cur.pos>prevPos){
        //generate new interval
        double avg = (double) totalSpeed/ (double)totalCars;
        res.add(new Interval(prevPos,cur.pos,avg));
      }
      if(cur.isLeft){
        totalCars++;
        totalSpeed += cur.speed;
      }else{
        totalCars--;
        totalSpeed -= cur.speed;
      }
      prevPos = cur.pos;
    }
    return res;
  }
  public List generateCarList(int[][] cars){
    List<Node> list = new ArrayList<>();
    for(int[] car : cars){
      list.add(new Node(car[0],car[2],true));
      list.add(new Node(car[1],car[2],false));
    }
    return list;
  }
  class Node{
    int pos;
    int speed;
    boolean isLeft;
    public Node(int p,int s,boolean l){
      this.pos = p;
      this.speed = s;
      this.isLeft = l;
    }
  }
  class Interval{
    int left,right;
    double speed;
    public Interval(int l,int r,double s){
      left = l;right = r;speed = s;
    }
  }
  public static void print(List<Interval> res){
    for(Interval e : res){
      System.out.print(e.left+" "+e.right+" "+e.speed+"; ");
    }
  }
}
