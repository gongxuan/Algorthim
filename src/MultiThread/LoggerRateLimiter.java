package MultiThread;

import java.util.*;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=565338&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class LoggerRateLimiter {
  LinkedHashMap<String,Integer> msgTS;
  class Entry{
    String msg;
    int ts;
    public Entry(String s,int ts){
      msg = s;
      this.ts = ts;
    }
  }
  int lastTimestamp = 0;
  public LoggerRateLimiter() {
    msgTS = new LinkedHashMap<String,Integer>(100, 0.75f, true){
      protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return lastTimestamp - eldest.getValue() > 10;
//Q: （面试官开始告诉我为什么会错）（假设题目要求T秒内print过的就不用再print）如果在t1时间有一串request (t1, s1), (t1, s2)... (t1, s_n) 但是字典可能会在比如 (t1, s3) 的时候就把t1-T 的value的全删完了
      }
    };

  }

  public boolean shouldPrintMessage(int timestamp, String message) {
    lastTimestamp = timestamp;
    Integer prev = msgTS.get(message);
    if(prev==null || timestamp-prev>=10){
      msgTS.put(message,timestamp);

      return true;
    }
    return false;
  }
}
