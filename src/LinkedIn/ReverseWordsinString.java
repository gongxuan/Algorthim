package LinkedIn;

public class ReverseWordsinString {
    public String reverseWords(String s) {
        StringBuffer buffer = new StringBuffer(s.trim());
        buffer = buffer.reverse();
        int fast = 0,slow=0;
        int countSpace = 0;  //count continuous space
        while(fast<=buffer.length()){
            if(fast<buffer.length() && buffer.charAt(fast)!=' '){
                fast++;
                countSpace++;
                if(countSpace>1){
                    buffer.deleteCharAt(fast-1);
                    countSpace--;
                }
                continue;
            }
            //reverse substring [slow,fast)
            int end = fast-1;
            while(slow<end){
                char temp = buffer.charAt(slow);
                buffer.setCharAt(slow,buffer.charAt(end));
                buffer.setCharAt(end,temp);
                slow++;
                end--;
            }

            fast++;
            slow = fast;
            countSpace = 0;
        }
        return buffer.toString();
    }
    public String reverseWords2(String s) {
        if (s.isEmpty()) return "";
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        int end = s.length() - 1;
        while (end >= 0) {
            while (end>=0 && s.charAt(end) ==' ')
                end--;
            int start = s.lastIndexOf(' ',end);
            if (start == -1) {
                sb.append(s.substring(0, end+1));
            } else {
                sb.append(s.substring(start+1, end+1)).append(" ");
            }
            end = start - 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseWordsinString r = new ReverseWordsinString();
        System.out.println(r.reverseWords2("hello   ,world"));
    }
}
