package LinkedIn;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    public List<String> paginate(String input, int pageLen){
        //null check
        String[] wordList = input.split(" ");
        List<String> res = new ArrayList<>();
        int curLen = 0;
        StringBuffer page = new StringBuffer();
        for(int i=0;i<wordList.length;){
            String word = wordList[i];
            if(word.length()+curLen<=pageLen){
                //can fit in
                curLen += word.length() + 1;    //with one space
                page.append(word+" ");
                i++;
            }else if(word.length()>pageLen){

                do{
                    int lastLen = Math.min(word.length(),pageLen - curLen);
                    page.append(word.substring(0,lastLen));
                    res.add(page.toString());
                    curLen = 0;
                    word = word.substring(lastLen);
                    page = new StringBuffer();
                }while(word.length()!=0);
                i++;
            }
            else{
                //can not fit in
                res.add(page.substring(0,page.length()-1));
                curLen = 0;
                page = new StringBuffer();
                //stay
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Pagination page = new Pagination();
        String input = "This is an example. hello world";
        String input1 = "This isanexample.helloworld";
        List<String> res = page.paginate(input1,4);
        for(String str : res){
            System.out.println(str);
        }
    }
}
