package LinkedIn;

public class StringReplacement {

    public String replaceRecursion(String orig,String find,String repl){
        int found = orig.indexOf(find);
        if(found==-1){
            return orig;
        }
        return orig.substring(0,found) + repl + replaceRecursion(orig.substring(found+find.length()),find,repl);
    }

    public String replace(String orig,String find,String repl){
        int index = 0;
        StringBuffer res = new StringBuffer();
        while(index<orig.length()){
            int found = orig.indexOf(find,index);
            if(found==-1){
                res.append(orig.substring(index));
                break;
            }else{
                res.append(orig.substring(index,found));
                res.append(repl);
                index = found + find.length();
            }
        }
        return res.toString();
    }
    //TODO   KMP
    public static void main(String[] args) {
        //abca  a  gg
        StringReplacement sr = new StringReplacement();
        String orig = "";
        String find = "a";
        String repl = "gg";
        System.out.println(sr.replaceRecursion(orig,find,repl));
        System.out.println(sr.replace(orig,find,repl));
    }
}
