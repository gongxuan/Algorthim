package Google.BinarySearch;

import java.util.List;

//https://www.1point3acres.com/bbs/thread-575823-1-1.html
//https://www.1point3acres.com/bbs/thread-576230-1-1.html
public class Interval {
  //国人小哥，给了一串tasks，每个task给了起止时间，每个task不能有overlap，给一个新的task，返回能否插入到那一串task里，要用二分。Followup是很多组tasks怎么办
  //insert intervals：一个CPU上一次只能执行一个任务，现在有一个list of task with starting time and ending time,且相互不重叠、冲突，问可不可以插入某个新任务;
  //follow up 1: 如果是一台四核的电脑呢？楼主仍然用的tuple+二分法；并且坦白说自己遇到过类似的题目（现在很后悔自己承认，因为说完面试官的表情变了一下下）
  // 这个follow up其实就是在任意时间段overlap的任务不超过4个就可以
  //follow up2: 如果在四核的电脑上插入 a list of new task呢？
  public static void main(String[] args) {

    int[][] tasks = {{0,3},{7,8},{11,14},{15,17}};
    Interval ins = new Interval();
    System.out.println(ins.canInsert(tasks,new int[]{5,6}));
    System.out.println(ins.canInsert(tasks,new int[]{5,7}));
    System.out.println(ins.canInsert(tasks,new int[]{5,9}));
  }
  public boolean canInsert(int[][] tasks,int[] todo){
    //boundary new task's start==end, int?floating points?
    int low = 0,hi = tasks.length - 1;
    int ceil = -1;
    while(low<=hi){
      int mid = low + (hi-low)/2;
      if(tasks[mid][1]>todo[0]){
        ceil = mid;
        hi = mid - 1;
      }else if(tasks[mid][1]<todo[0]){
        low = mid + 1;
      }else{
        return false;
      }
    }
    if(ceil==-1){
      //all tasks' end is smaller than new's start
      return true;
    }
    return todo[1]<tasks[ceil][0];
  }
  public boolean canInsert(int[][] tasks, int[][] list){
    //boundary new task's start==end, int?floating points?
    //sort list and check list if it has overlapped tasks or not. sweep line
    //if no overlap, check new task one by one with tasks.
    return true;
  }

}
