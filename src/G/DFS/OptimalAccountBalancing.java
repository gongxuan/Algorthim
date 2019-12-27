package G.DFS;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=570847&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class OptimalAccountBalancing {
//465，输入输出都有些变化，
// 输入不是List<{借方,贷方,金额}>，而是List<{借方,金额}>，默认是一人代付，所有人均摊的模式，
// 输出也不都是最少转账数，要求返回最佳平帐方案，类似List<{付款人,收款人,金额}>

  //A group of friends went out for an outing. Several people paid for the costs, and others didn't pay.
  // Determine how much money should each payer get back. Follow up, determine how the group should settle up the costs.
  // Output should be a list of tuples, with (payer, payee, cost) which means payer should give payee 'cost' amount of money.
  int minTxn=Integer.MAX_VALUE;
  List<List<Integer>> res;
  public void dfs(int curIdx, List<Integer> debts,List<List<Integer>> txn,int times){
    int size = debts.size();
    while(curIdx<size && debts.get(curIdx)==0){
      curIdx++;
    }
    if(curIdx==debts.size()){
      if(minTxn>times){
        res = new ArrayList<>(txn);
        minTxn = times;
      }
      return ;
    }
    int curDebt = debts.get(curIdx);
    for(int i=curIdx+1;i<size;i++){
      if((curDebt^debts.get(i))>=0 )
        continue;
      debts.set(i,debts.get(i)+curDebt);
      if(curDebt>0)
        txn.add(new ArrayList<>(Arrays.asList(new Integer[]{curIdx, i, curDebt})));
      else{
        txn.add(new ArrayList<>(Arrays.asList(new Integer[]{i, curIdx, -curDebt})));
      }
      dfs(i+1,debts,txn,times+1);
      debts.set(i,debts.get(i)-curDebt);
    }
  }
  public int minTransfers() {
    List<Integer> debts = new ArrayList<>();
    debts.add(-4);
    debts.add(4);
    debts.add(0);
    List<List<Integer>> txn = new ArrayList<>();
    dfs(0,debts,txn,0);

    for(List<Integer> e : res){
      System.out.println(e.get(0)+" "+e.get(1)+" "+e.get(2));
    }
    System.out.println(minTxn);
    return minTxn;
  }

  public static void main(String[] args) {
    OptimalAccountBalancing o = new OptimalAccountBalancing();
    o.minTransfers();
  }
}
