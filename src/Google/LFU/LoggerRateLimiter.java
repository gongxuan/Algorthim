package Google.LFU;

import java.util.*;

public class LoggerRateLimiter {
  //https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=564071&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
  //https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=565338&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
  //integer to represent TS? , log comes in service in ascending order?
  //char in msg?  how many API calls?
  //solution1) Map<msg,timestamp> update when calling method.
  //defect, entry in map is increasing.
  //rm useless msgs.
  //solution2: linkedHashMap
  //solution3: only store recent Logs ,when log is more than 10s rm it.
  //LFU???
  //1M calls arrive at same time
  //1M calls arrive at same time with diff msgs
  LinkedHashMap<Integer, HashSet<String>> timeMsg;
  Map<String,Integer> msgTime;
  int latestTime = 0;
  private static final int EXP = 10;
  public LoggerRateLimiter() {
    timeMsg = new LinkedHashMap<Integer, HashSet<String>>(100, 0.75f, true){
      protected boolean removeEldestEntry(Map.Entry<Integer, HashSet<String>> eldest) {
        return latestTime - eldest.getKey() > EXP;
      }
    };

  }

//  public boolean shouldPrintMessage(int timestamp, String message) {
//    Iterator<Map.Entry<Integer,HashSet<String>>>  it = timeMsg.entrySet().iterator();
//    latestTime = timestamp;
//    boolean should = false;
//    while(it.hasNext()){
//      Map.Entry<Integer,HashSet<String>> eldest = it.next();
//      if(latestTime - eldest.getKey()>EXP){
//        if(eldest.getValue().contains(message)){
//          should = true;
//          break;
//        }
//      }
//    }
//    return should;
//  }
}
