import java.util.ArrayList;
import java.util.Arrays;

public class CircularSuffixArray {
    private class suffix implements Comparable<suffix>{
        public static String origstring;
        public int origindex;

        public suffix(int index){
            origindex = index;
        }

        private char suffix_get(int index){
            return origstring.charAt((index + this.origindex) % origstring.length());
        }

        @Override
        public int compareTo(suffix o) {
            int len = origstring.length();
            for(int i = 0;i < len;i++){
                char this_c = this.suffix_get(i);
                char that_c = o.suffix_get(i);

                if(this_c > that_c) return +1;
                else if(this_c < that_c) return -1;

            }
            return 0;
        }
    }

    suffix[] suffixes;

    public CircularSuffixArray(String origString){
        suffix.origstring = origString;
        int len = suffix.origstring.length();
        suffixes = new suffix[len];

        for(int i=0; i < len; i++){
            suffixes[i] = new suffix(i);
        }

        Arrays.sort(suffixes);
    }

    public int length(){
        return suffix.origstring.length();
    }

    public int index(int i){
        return suffixes[i].origindex;
    }

    public char getlast(int i){
        return suffixes[i].suffix_get(suffix.origstring.length() -1 );
    }

    public static void main(String[] args){
        In in = new In("abra.txt");
        String s = in.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);

        for(int i=0;i<s.length();i++){
            System.out.print(csa.index(i));
            System.out.println(" " + csa.suffixes[i].suffix_get(s.length() -1 ));
        }
    }
}
