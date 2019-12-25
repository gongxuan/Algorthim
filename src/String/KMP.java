package String;

public class KMP {
    public void kmp(String text,String pattern){
        char[] pArr = pattern.toCharArray();
        char[] tArr = text.toCharArray();
        int tLen = tArr.length;
        int pLen = pattern.length();
        int[] lps = lpsHelper(pArr);
        for(int e : lps){
            System.out.print(e+" ");
        }
        System.out.println();
        int i=0,j=0;
        while(i<tLen){
            if(tArr[i]==pArr[j]){
                i++;
                j++;
            }
            if(j==pLen) {   //all matched
                System.out.print(i-j+" ");
                j = lps[j-1];
            }else if(i<tLen && tArr[i]!=pArr[j]){  //not matched
                if(j==0){   //nothing matched
                    i++;
                }else{
                    j = lps[j-1];
                }
            }
        }
    }

    public int[] lpsHelper(char[] pattern){
        int pLen = pattern.length;
        int[] lps = new int[pLen];
        int prevLpsLen = 0;
        int i = 1;
        lps[0] = 0;
        while(i<pLen){
            if(pattern[i]==pattern[prevLpsLen]){    //match
                prevLpsLen++;
                lps[i] = prevLpsLen;
                i++;
            }else{  //not match
                if(prevLpsLen>0){   //try to match prev lps
                    prevLpsLen = lps[prevLpsLen-1];
                }else{  //nothing matched
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        KMP k = new KMP();
        String text = "AAAABAACBAAAAh";
        String pattern = "AAAA";
        k.kmp(text,pattern);
    }
}

