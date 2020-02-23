package Yelp.OA;

import java.util.*;
import java.util.List;

public class ActiveBusiness {
//题目说的是输入一些事件，事件的属性包括类型（photo_view, ads, page_views, reviews 四种）, 次数，Business ID
//    当一个Business有两种事件的次数等于或超过该事件在所有Business中次数的平均值，那么视这个事件为ActiveBusiness。
//    要求输出所有ActiveBusiness的Business ID。
//    相同Business ID的相同事件只会有一个条目记录总次数。
//
//    举个例子：
//        events.add(new Event("ads", 7, 3));
//        events.add(new Event("ads", 8, 2));
//        events.add(new Event("ads", 5, 1));
//        events.add(new Event("page_views", 11, 2));
//        events.add(new Event("page_views", 12, 3));
//        events.add(new Event("photo_views", 10, 1));
//        events.add(new Event("reviews", 7, 2));
//
//        上面的事件中可以确定2和3是active business，因为ads事件的平均次数是 7 + 8 + 5 / 3 = 6.67，page_views是 11 + 12 / 2 = 11.5，photo_views是10，reviews是7。
//        2的ads和reviews满足条件，3的ads和page_views满足条件，但是1只有一个photo_views满足。（有两个或两个以上类型满足就算）

  public List<Integer> find(List<Event> events){
    ERecord[] records = new ERecord[4];
    for(int i=0;i<4;i++){
      records[i] = new ERecord(0,0,0);
    }
    List<Integer> res = new ArrayList<>();
    Map<Integer,Integer[]> idToTypeFreq = new HashMap<>();
    for(Event event : events) {
      if (!idToTypeFreq.containsKey(event.id)) {
        idToTypeFreq.put(event.id, new Integer[]{0, 0, 0, 0});
      }
      switch (event.type) {
        case "photo_views":
          records[0].total += event.times;
          records[0].eventTimes++;
          idToTypeFreq.get(event.id)[0] += event.times;
          break;
        case "ads":
          records[1].total += event.times;
          records[1].eventTimes++;
          idToTypeFreq.get(event.id)[1] += event.times;
          break;
        case "page_views":
          records[2].total += event.times;
          records[2].eventTimes++;
          idToTypeFreq.get(event.id)[2] += event.times;
          break;
        case "reviews":
          records[3].total += event.times;
          records[3].eventTimes++;
          idToTypeFreq.get(event.id)[3] += event.times;
          break;
      }
    }
    //calculate avg
    for(int i=0;i<4;i++){
      if(records[i].eventTimes==0)  continue;
      records[i].avgs = (double)records[i].total/(double)records[i].eventTimes;
    }
    for(Map.Entry<Integer,Integer[]> entry : idToTypeFreq.entrySet()){
      int count = 0;
      int businessId = entry.getKey();
      Integer[] typeFreq = entry.getValue();
      for(int i=0;i<4;i++){
        if(typeFreq[i]>=records[i].avgs){
          count++;
          if(count>=2){
            res.add(businessId);
            break;
          }
        }
      }
    }
    return res;
  }
  class ERecord{
    int total,eventTimes;
    double avgs;
    public ERecord(int t,int e,double a){
      this.total = t;
      this.eventTimes = e;
      this.avgs = a;
    }
  }
  public static void main(String[] args) {
    List<Event> events = new ArrayList<>();
    events.add(new Event("ads", 7, 3));
    events.add(new Event("ads", 8, 2));
    events.add(new Event("ads", 5, 1));
    events.add(new Event("page_views", 11, 2));
    events.add(new Event("page_views", 12, 3));
    events.add(new Event("photo_views", 10, 1));
    events.add(new Event("reviews", 7, 2));
    ActiveBusiness ins = new ActiveBusiness();
    List<Integer> res = ins.find(events);
    for(int e : res){
      System.out.print(e+" ");
    }
    System.out.println();
  }
}
class Event{
  String type;
  int times;
  int id;
  public Event(String t,int times,int id){
    this.type= t;
    this.times = times;
    this.id = id;
  }
}
