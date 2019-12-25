package G;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=574174&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class PrintLog {
//题目是设计一个logger类，有start(request_id)和finish(request_id)两个方法，要求logger按照request的起始时间排序输出所有log。
  //clarify: type of id? TS is given? print method?valid input?
  //List store finished logs
  //Map<Id,Log> store logs, if it's finished, add it to list so it's easy to print finished logs
  List<Log> finishedLogs;
  Map<Integer,Log> idLogMap;
  public PrintLog(){
    finishedLogs = new ArrayList<>();
    idLogMap = new HashMap<>();
  }
  public void start(int id,int timestamp){
    idLogMap.put(id,new Log(timestamp));
  }
  public void end(int id,int timestamp){
    Log cur = idLogMap.get(id);
    cur.end = timestamp;
    finishedLogs.add(cur);
  }
  class Log{
    int start,end;
    public Log(int s){
      start  = s;

    }
  }
}
