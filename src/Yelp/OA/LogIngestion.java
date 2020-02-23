package Yelp.OA;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=554315&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D36%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D1%26searchoption%255B3109%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
public class LogIngestion {
  //Sample Input:
  //        current_user_list: [
  //            User({
  //                id: 1,
  //                opted_in: false
  //            })],
  //opt_in_change_log: [
  //            OptInChange({
  //                user_id: 19,
  //                action: 'opt_out'
  //            })]
  class User{
    int id;
    boolean optedIn;
    public User(int id,boolean o){
      this.id = id;
      this.optedIn = o;
    }
  }
  class OptInChange{
    int id;
    String action;
    public OptInChange(int id,String o){
      this.id = id;
      this.action = o;
    }
  }
  public List<Integer> log(List<User> curr,List<OptInChange> logs){
    Map<Integer,Boolean> changeLogMap = new HashMap<>();
    for(OptInChange log : logs){
      changeLogMap.put(log.id,log.action=="opt_out"?false:true);
    }
    List<Integer> res = new ArrayList<>();
    for(User user : curr){
      if(changeLogMap.containsKey(user.id) && user.optedIn!=changeLogMap.get(user.id)){
        res.add(user.id);
      }
    }
    return res;
  }
}
