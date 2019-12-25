package G.Array;

public class KMP {
    public int[] computeLsp(String pattern) {
        int[] lsp = new int[pattern.length()];
        lsp[0] = 0;  // Base case
        for (int i = 1; i < pattern.length(); i++) {
            // Start by assuming we're extending the previous LSP
            int j = lsp[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
                j = lsp[j - 1];
            if (pattern.charAt(i) == pattern.charAt(j))
                j++;
            lsp[i] = j;
        }
        return lsp;
    }
    public static void main(String[] arg){
        KMP kmp  =new KMP();
        String text = "ABABACABABAB";
        String pattern = "ABABAB";
        int[] lps =  kmp.computeLsp(pattern);
        print(lps);
        kmp.KMPSearch(pattern,text);
    }
    static void  print(int[] lps){
        for(int i=0;i<lps.length;i++){
            System.out.print(lps[i]+" ");
        }
        System.out.println();

    }
    void KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        int[] lps = computeLsp(pat);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                    + "at index " + (i - j));
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }


}
