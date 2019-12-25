package LinkedIn;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

public class MaxCoLine {
    public int maxPoints(int[][] points) {
        //slope + node determine a line
        //n^2
        Map<BigDecimal,Integer> countMap = new HashMap<>();
        int max = 0;
        int len = points.length;
        for(int i=0;i<len;i++){
            countMap.clear();
            //fix one point first
            int countVertical = 0;
            int samePoint = 1;
            for(int j=i+1;j<len;j++){
                //calculate every other points and current point's slop
                if(points[j][0]==points[i][0] && points[j][1]==points[i][1]){
                    samePoint++;
                }else if(points[j][0]==points[i][0]){
                    countVertical++;
                }else{
                    // double slope = ((double)(points[j][1])-(double)(points[i][1]))/
                    //     ((double)(points[j][0])-(double)(points[i][0]));
                    BigDecimal slope = BigDecimal.valueOf(points[j][1]-points[i][1]).
                            divide(BigDecimal.valueOf(points[j][0]-points[i][0])
                                    , new MathContext(20));
                    countMap.put(slope,countMap.getOrDefault(slope,0)+1);
                }
            }
            int curMaxOtherNodes = countVertical;
            for(Map.Entry e : countMap.entrySet()){
                curMaxOtherNodes = Math.max(curMaxOtherNodes, (Integer)e.getValue());
            }
            max = Math.max(max,curMaxOtherNodes+samePoint); //including self
        }
        return max;
    }

    public static void main(String[] args) {
        MaxCoLine m = new MaxCoLine();
        int[][] points = {{2,3},{3,3},{-5,3}};
        System.out.println(m.maxPoints(points));
    }
}
