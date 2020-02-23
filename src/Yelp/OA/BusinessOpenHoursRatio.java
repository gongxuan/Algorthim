package Yelp.OA;

import java.util.List;

public class BusinessOpenHoursRatio {
  public float BusinessOpenHoursRatio(int[] query, List<Integer[]> openHours){
    if(openHours == null || openHours.size() == 0)
      return 0.0f;

    float totalQueryTime = (float)query[1] - (float)query[0];
    float validTime = 0.0f;
    for(Integer[] openRange : openHours){
      float[] valid = new float[2];
      valid[0] = Math.max((float)openRange[0],(float)query[0]);
      valid[1] = Math.min((float)openRange[1],(float)query[1]);
//      if(openRange[0] <= query[0] && openRange[1] >= query[1]){//the query time include in open hours
//        validTime += totalQueryTime;
//      } else if(openRange[0] >= query[0] && openRange[1] <= query[1]){//the open hours include in query time
//        validTime += (float)openRange[1]-(float)openRange[0];
//      } else {
//        if(openRange[0] <= query[0] && openRange[1] <= query[1] && openRange[1] >= query[0]){
//          validTime += (float)openRange[1] - (float)query[0];
//        }
//        if(query[0] <= openRange[0] && query[1] <= openRange[1] && openRange[1] >= query[1]){
//          validTime += (float)query[1] -  (float)openRange[0];
//        }
//      }
      validTime += valid[1] - valid[0];
    }
    //Float.intBitsToFloat(right_time)/Float.intBitsToFloat(query_time);
    return validTime/totalQueryTime;
  }
}
