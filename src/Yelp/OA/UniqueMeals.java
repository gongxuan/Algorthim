package Yelp.OA;

import java.util.*;
public class UniqueMeals {
  private static class Meal {
    String name;
    String[] ingredients;

    public Meal(String n, String[] ingredients) {
      this.name = n;
      this.ingredients = ingredients;
    }
  }

  private int uniqueMealsSize(List<Meal> meals){
    Set<String> set = new HashSet<>();
    for(Meal meal : meals){
      String[] arr = meal.ingredients;
      Arrays.sort(arr);
      set.add(convert(arr));
    }
    return set.size();
  }
  public String convert(String[]  arr){
    StringBuffer sb = new StringBuffer();
    for(String ingredient : arr){
      sb.append(ingredient+" ");
    }
    Collections.sort(new ArrayList<Integer>());
    return sb.toString();
  }
}


