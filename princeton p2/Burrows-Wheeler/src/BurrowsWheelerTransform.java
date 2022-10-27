

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BurrowsWheelerTransform {
    public static void transform(BinaryIn in, BinaryOut out){
        StringBuilder builder = new StringBuilder();
        while (! in.isEmpty()){
            builder.append(in.readChar());
        }
        String str = builder.toString();
        CircularSuffixArray csa = new CircularSuffixArray(str);

        int len = str.length();
        int zeroindex= -1;
        char[] lastcharacters = new char[len];

        for(int i=0;i<len;i++){
            lastcharacters[i] = csa.getlast(i);
            if(csa.index(i) == 0){
                zeroindex = i;
            }
        }

        out.write(zeroindex);
        System.out.println(zeroindex);

        for (int i = 0;i< len;i++){
            out.write(lastcharacters[i], 8);
            System.out.println(lastcharacters[i]);
        }

        out.flush();
        out.close();
    }

    public static void inverseTransform(BinaryIn in, BinaryOut out){
        HashMap<Character, queue<Integer>> lastcolumn = new HashMap<Character, queue<Integer>>();
        ArrayList<Character> firstcolumn = new ArrayList<Character>();
        ArrayList<Character> Lastcolumnarray = new ArrayList<>();

        int firstindex = in.readInt(32);
        int i=0;
        while(! in.isEmpty()){
            char c = in.readChar();
            if(lastcolumn.containsKey(c))lastcolumn.get(c).enque(i);
            else{
                lastcolumn.put(c, new queue<Integer>());
                lastcolumn.get(c).enque(i);
            }
            firstcolumn.add(c);
            Lastcolumnarray.add(c);
            i++;
        }
        /*Character[] firscol = (Character[]) firstcolumn.toArray();*/
        char[] firstcolumnc = radixsort(firstcolumn);
        firstcolumn = null;
        int[] next = new int[i];
        i=0;
        for(Character c: firstcolumnc){
            next[i] = lastcolumn.get(c).deque();
            i++;
        }
        int crrelement = next[firstindex];
        for(int j =0; j<i;j++){
            char c = Lastcolumnarray.get(crrelement);
            out.write(c, 8);
            System.out.println(c);
            crrelement = next[crrelement];
        }

        out.flush();
        out.close();
    }

    public static char[] radixsort(ArrayList<Character> arl){
        int[] freq = new int[257];
        int len = arl.size();
        for(int i=0;i< len;i++){
            char c = arl.get(i);
            freq[c+1]++;
        }

        for(int i=1; i< 257; i++){
            freq[i] += freq[i-1];
        }

        char[] adj = new  char[len];
        for(char c:arl){
            adj[freq[c]++] = c;
        }
        return adj;
    }

    public static void main(String[] args){
        BinaryIn in = new BinaryIn("abra.txt");
        BinaryOut out = new BinaryOut("eşek.txt");

        BurrowsWheelerTransform.transform(in, out);

        System.out.println("-----------------------------------------------");

        BinaryIn in2 = new BinaryIn("eşek.txt");
        BinaryOut out2 = new BinaryOut("abra.txt");

        BurrowsWheelerTransform.inverseTransform(in2, out2);

    }
}
