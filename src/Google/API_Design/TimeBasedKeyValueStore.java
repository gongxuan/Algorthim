package Google.API_Design;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=564071&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class TimeBasedKeyValueStore {
//也是先一个简单的问题，输入一个array，元素都是word@timestamp的形式，只考虑10的interval，打印出所有的word，10s内重复的只打印一遍。
//eg.["foo@1", "bar@2", "foo@5", "bar@20"]
//output = ["foo", "bar", "bar"] 比较简单hashmap即可
//之后问如果10s内重复的全都不打印如何改进？
//即变为output = ["bar", "bar"]
}
