package G.BinarySearch;

import java.util.*;

public class OnlineElection {
  //follow up 是给个time，输出topN的candidates。
  //maintain top N for every date.
  //Map<number of votes, list of persons>
  //top K: treeMap. lgN time for searching
  //Map<person,his or her votes>
  //linkedHashSet store data in insertion order. we modify it to store reverse order of intersion.
  // If tie, get head of the set.
  TreeMap<Integer,LinkedHashSet<Integer>> voteToPersons;
  Map<Integer,Integer> personToVotes;
  List<Integer>[] topKVotes;
  TreeMap<Integer,Integer> timeIdx;
  int N,K;
  public OnlineElection(int[] persons, int[] times) {
    //tie, cast time on t? times array sorted?
    //how many calls of query?
    //time is sorted, blananced tree or binary search on times.
    //pre-processing top 1 , variable: maxVote
    //follow up: LFU?
    //<Time,linkedHashSet>
    N = persons.length;
    K = 1;
    personToVotes = new HashMap<>();
    voteToPersons = new TreeMap<>();
    topKVotes = new List[N];  //top K votes for every time
    timeIdx = new TreeMap<>();
    computeTopVotes(persons,times);
  }

  public void computeTopVotes(int[] persons, int[] times){
    //1.find person and its votes, update votes, 2. find top one.
    //follow-up: top N
    for(int i=0;i<N;i++){
      addVote(persons[i]);
      topKVotes[i] = topK();
      timeIdx.put(times[i],i);
    }

  }
  public void addVote(int person){
    int oldVotes = personToVotes.getOrDefault(person,0);
    //rm person from hashSet
    int newVotes = oldVotes+1;
    if(voteToPersons.containsKey(oldVotes))
      voteToPersons.get(oldVotes).remove(person);
    LinkedHashSet<Integer> persons = voteToPersons.getOrDefault(newVotes,new LinkedHashSet<>());
    persons.add(person);
    voteToPersons.put(newVotes,persons);
    personToVotes.put(person,newVotes);
  }
  public List<Integer> topK(){
    int countP = 0;
    List<Integer> res = new ArrayList<>();
    for(Map.Entry<Integer,LinkedHashSet<Integer>> e : voteToPersons.descendingMap().entrySet()){
      countP += e.getValue().size();
      //persons have same votes
      List<Integer> personsWithSameVotes = new ArrayList<>(e.getValue());
      Collections.reverse(personsWithSameVotes);
      res.addAll(personsWithSameVotes);
      if(countP>=K){
        break;
      }
    }
    //rm redundant persons
    while(countP>K){
      res.remove(res.size()-1);
      countP--;
    }
    return res;
  }

  public int q(int t) {
    int idx = timeIdx.floorEntry(t).getValue();
    return topKVotes[idx].get(0);
  }

  public static void main(String[] args) {
    int[] persons = {0,1,1,0,0,1,0};
    int[] times = {0,5,10,15,20,25,30};
    //[3],[12],[25],[15],[24],[8]
    OnlineElection ins = new OnlineElection(persons,times);
    System.out.println(ins.q(3));
    System.out.println(ins.q(12));
    System.out.println(ins.q(25));
    System.out.println(ins.q(15));
    System.out.println(ins.q(24));
    System.out.println(ins.q(8));

  }
}
