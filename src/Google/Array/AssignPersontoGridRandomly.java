package Google.Array;

import java.util.*;

public class AssignPersontoGridRandomly {
//ABC小姐姐，笑容很甜，题目是给一个2d grid，把格子随机分配给n个人，每个人分到的格子数要一样，返回结果是2d数组，里面的值是不同人的编号
  List<Integer> persons = new ArrayList<>();
  Map<Integer,Integer> freq = new HashMap<>();
  int K;
  public int[][] assign(int R,int C,int n){
    int[][] grid = new int[R][C];
    int total = R*C;
    if(total%n!=0){
      return null;
    }
    K = total/n;
    int idx = 0;
    Random random = new Random();
    for(int i=0;i<n;i++){
      persons.add(i);
    }

    while(idx<total){
      int r = idx/C;
      int c = idx%C;
      int ri = random.nextInt(persons.size());
      int person = persons.get(ri);
      updateFreq(ri);
      grid[r][c] = person;
      idx++;
    }
    return grid;
  }
  public void updateFreq(int pIdx){
    int person = persons.get(pIdx);
    freq.put(person,freq.getOrDefault(person,0)+1);
    if(freq.get(person)==K){
      removePerson(pIdx);
    }
  }
  public void removePerson(int idx){
    if(idx==persons.size()-1){
      persons.remove(idx);
      return ;
    }
    int last = persons.get(persons.size()-1);
    persons.set(idx,last);
    persons.remove(persons.size()-1);
  }

  public static void main(String[] args) {
    AssignPersontoGridRandomly ins = new AssignPersontoGridRandomly();
    int[][] grid = ins.assign(2,2,2);
  }

  public int[][] reservoirSampling(){
    //swap curr value with all previous values including its self
    return null;
  }
}
