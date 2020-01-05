package Google.Graph;

import java.util.*;

//https://www.1point3acres.com/bbs/thread-580306-1-1.html

public class WriteNameOnPlanet {
  //第四轮 一个奇怪的设计题，给你一个城市，每户人家都有自己的邻居，并且所有人都只能直接访问自己的邻居，有一户人准备写一本族谱，
  // 他应该在书上写什么instruction能够把所有住户的名字写在族谱里，注意：住户之间可能重名，并且没有unique identifier
  //第四题说的不太清楚 大概就是这个initiator要把书传给自己的邻居，并且在书上为后续拿到书的人写下一些指导，然后最后当这个人拿回书时，
  // 书上已经写了所有住户的名字
  Map<Person, Set<Person>> graph;
  public List<String> findAllNames(Map<Person, Set<Person>> graph){
    return null;
  }
}
class Person{
  boolean visited;
  String name;
  public Person(){
  }
}

//我做的时候设计个好几个类 关键是一个住户只能被访问一次 你可以用一个标志位表示 这一轮主要是聊怎么work 感觉code不是重点
//补充内容 (2019-12-22 22:42):
//整个流程其实就是一个dfs 你要做的只是避免无限循环
