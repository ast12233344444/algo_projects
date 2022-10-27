import java.util.ArrayList;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

public class Wordnet {
    digraph D;
    SAP sap;

    public Wordnet(String synsets, String hypernyms){
        D = new digraph(synsets, hypernyms);
        sap = new SAP(D);
        if(D.checkcyclicpattern()){throw new IllegalArgumentException("dairesel graf!!");}
    }

    public Iterable<String> nouns(){
        String[] s =(String[])D.synset_to_word.keySet().toArray();
        return Arrays.asList(s);
    }

    public boolean isnoun(String s){
        return D.synset_to_word.containsKey(s);
    }

    public int distance(String a, String b){
        return sap.length(a,b);
    }

    public String[] sap(String a, String b){
        return sap.sca(a,b);
    }
}
